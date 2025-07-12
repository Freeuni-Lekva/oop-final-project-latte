<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Quiz</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
        }
        .form-group {
            margin-bottom: 20px;
            display: flex;
            align-items: center;
        }
        .form-group label {
            width: 150px;
            font-weight: bold;
        }
        .form-group input[type="text"],
        .form-group textarea {
            width: 300px;
            padding: 8px;
        }
        .form-group textarea {
            height: 100px;
            resize: vertical;
        }
        .checkbox-group {
            margin-bottom: 20px;
        }
        .checkbox-group label {
            margin-right: 20px;
        }
        .submit-button {
            padding: 10px 20px;
            font-size: 16px;
        }
        .question-block {
            border: 1px solid #ccc;
            padding: 15px;
            margin-top: 15px;
        }
        .question-block h4 {
            margin-top: 0;
        }
        .inline-form {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .inline-form label {
            width: 150px;
            font-weight: bold;
        }
        .inline-form input {
            flex: 1;
            padding: 8px;
        }
        #add-question-controls {
            margin-top: 30px;
        }
        #add-question-controls > *:not(:first-child) {
            margin-left: 10px;
        }
    </style>

    <script>
        let questionCount = 0;

        function validateForm() {
            const name = document.getElementById("quizName").value.trim();
            const description = document.getElementById("quizDescription").value.trim();

            if (name === "") {
                alert("Quiz name is required.");
                return false;
            }
            if (description === "") {
                alert("Quiz description is required.");
                return false;
            }

            return true;
        }

        function addQuestion() {
            const type = document.getElementById("questionTypeSelector").value;
            if (!type) {
                alert("Please select a question type.");
                return;
            }

            const container = document.getElementById("questions-container");
            const questionDiv = document.createElement("div");
            questionDiv.className = "question-block";

            createQuestionElements(questionDiv, type, questionCount);
            container.appendChild(questionDiv);

            if (type === "multiple-choice") {
                addIncorrectAnswer(questionCount);
            }

            questionCount++;
        }

        function createQuestionElements(container, type, index) {
            const h4 = document.createElement("h4");

            switch (type) {
                case "question-response":
                    h4.textContent = "Question-Response";
                    container.appendChild(h4);

                    // Question input
                    const questionDiv1 = document.createElement("div");
                    questionDiv1.className = "inline-form";
                    const questionLabel1 = document.createElement("label");
                    questionLabel1.textContent = "Question:";
                    const questionInput1 = document.createElement("input");
                    questionInput1.type = "text";
                    questionInput1.name = "questions[" + index + "].text";
                    questionInput1.required = true;
                    questionDiv1.appendChild(questionLabel1);
                    questionDiv1.appendChild(questionInput1);
                    container.appendChild(questionDiv1);

                    const answerDiv1 = document.createElement("div");
                    answerDiv1.className = "inline-form";
                    const answerLabel1 = document.createElement("label");
                    answerLabel1.textContent = "Correct Answer:";
                    const answerInput1 = document.createElement("input");
                    answerInput1.type = "text";
                    answerInput1.name = "questions[" + index + "].answer";
                    answerInput1.required = true;
                    answerDiv1.appendChild(answerLabel1);
                    answerDiv1.appendChild(answerInput1);
                    container.appendChild(answerDiv1);

                    const typeInput1 = document.createElement("input");
                    typeInput1.type = "hidden";
                    typeInput1.name = "questions[" + index + "].type";
                    typeInput1.value = "question-response";
                    container.appendChild(typeInput1);

                    const deleteBtn1 = document.createElement("button");
                    deleteBtn1.type = "button";
                    deleteBtn1.textContent = "Delete Question";
                    deleteBtn1.onclick = function() { deleteQuestion(this); };
                    container.appendChild(deleteBtn1);
                    break;

                case "picture-response":
                    h4.textContent = "Picture-Response";
                    container.appendChild(h4);

                    const questionDiv3 = document.createElement("div");
                    questionDiv3.className = "inline-form";
                    const questionLabel3 = document.createElement("label");
                    questionLabel3.textContent = "Question:";
                    const questionInput3 = document.createElement("input");
                    questionInput3.type = "text";
                    questionInput3.name = "questions[" + index + "].text";
                    questionInput3.required = true;
                    questionDiv3.appendChild(questionLabel3);
                    questionDiv3.appendChild(questionInput3);
                    container.appendChild(questionDiv3);

                    const imageDiv = document.createElement("div");
                    imageDiv.className = "inline-form";
                    const imageLabel = document.createElement("label");
                    imageLabel.textContent = "Image URL:";
                    const imageInput = document.createElement("input");
                    imageInput.type = "text";
                    imageInput.name = "questions[" + index + "].imageUrl";
                    imageInput.required = true;
                    imageDiv.appendChild(imageLabel);
                    imageDiv.appendChild(imageInput);
                    container.appendChild(imageDiv);

                    const answerDiv3 = document.createElement("div");
                    answerDiv3.className = "inline-form";
                    const answerLabel3 = document.createElement("label");
                    answerLabel3.textContent = "Correct Answer:";
                    const answerInput3 = document.createElement("input");
                    answerInput3.type = "text";
                    answerInput3.name = "questions[" + index + "].answer";
                    answerInput3.required = true;
                    answerDiv3.appendChild(answerLabel3);
                    answerDiv3.appendChild(answerInput3);
                    container.appendChild(answerDiv3);

                    const typeInput3 = document.createElement("input");
                    typeInput3.type = "hidden";
                    typeInput3.name = "questions[" + index + "].type";
                    typeInput3.value = "picture-response";
                    container.appendChild(typeInput3);

                    const deleteBtn3 = document.createElement("button");
                    deleteBtn3.type = "button";
                    deleteBtn3.textContent = "Delete Question";
                    deleteBtn3.onclick = function() { deleteQuestion(this); };
                    container.appendChild(deleteBtn3);
                    break;

                case "fill-blank":
                    h4.textContent = "Fill in the Blank";
                    container.appendChild(h4);

                    const questionDiv4 = document.createElement("div");
                    questionDiv4.className = "inline-form";
                    const questionLabel4 = document.createElement("label");
                    questionLabel4.textContent = "Full sentence (use ___ for blank):";
                    const questionInput4 = document.createElement("input");
                    questionInput4.type = "text";
                    questionInput4.name = "questions[" + index + "].text";
                    questionInput4.required = true;
                    questionInput4.placeholder = "e.g., The capital of France is ___";
                    questionDiv4.appendChild(questionLabel4);
                    questionDiv4.appendChild(questionInput4);
                    container.appendChild(questionDiv4);

                    const answerDiv4 = document.createElement("div");
                    answerDiv4.className = "inline-form";
                    const answerLabel4 = document.createElement("label");
                    answerLabel4.textContent = "Correct Word:";
                    const answerInput4 = document.createElement("input");
                    answerInput4.type = "text";
                    answerInput4.name = "questions[" + index + "].answer";
                    answerInput4.required = true;
                    answerInput4.placeholder = "e.g., Paris";
                    answerDiv4.appendChild(answerLabel4);
                    answerDiv4.appendChild(answerInput4);
                    container.appendChild(answerDiv4);

                    const typeInput4 = document.createElement("input");
                    typeInput4.type = "hidden";
                    typeInput4.name = "questions[" + index + "].type";
                    typeInput4.value = "fill-blank";
                    container.appendChild(typeInput4);

                    const deleteBtn4 = document.createElement("button");
                    deleteBtn4.type = "button";
                    deleteBtn4.textContent = "Delete Question";
                    deleteBtn4.onclick = function() { deleteQuestion(this); };
                    container.appendChild(deleteBtn4);
                    break;

                case "multiple-choice":
                    h4.textContent = "Multiple Choice";
                    container.appendChild(h4);

                    const questionDiv2 = document.createElement("div");
                    questionDiv2.className = "inline-form";
                    const questionLabel2 = document.createElement("label");
                    questionLabel2.textContent = "Question:";
                    const questionInput2 = document.createElement("input");
                    questionInput2.type = "text";
                    questionInput2.name = "questions[" + index + "].text";
                    questionInput2.required = true;
                    questionDiv2.appendChild(questionLabel2);
                    questionDiv2.appendChild(questionInput2);
                    container.appendChild(questionDiv2);

                    const correctDiv = document.createElement("div");
                    correctDiv.className = "inline-form";
                    const correctLabel = document.createElement("label");
                    correctLabel.textContent = "Correct Answer:";
                    const correctInput = document.createElement("input");
                    correctInput.type = "text";
                    correctInput.name = "questions[" + index + "].correct";
                    correctInput.required = true;
                    correctDiv.appendChild(correctLabel);
                    correctDiv.appendChild(correctInput);
                    container.appendChild(correctDiv);

                    const incorrectContainer = document.createElement("div");
                    incorrectContainer.id = "incorrect-container-" + index;
                    container.appendChild(incorrectContainer);

                    const addIncorrectDiv = document.createElement("div");
                    addIncorrectDiv.className = "form-group";
                    const emptyLabel = document.createElement("label");
                    const addIncorrectBtn = document.createElement("button");
                    addIncorrectBtn.type = "button";
                    addIncorrectBtn.textContent = "Add Incorrect Answer";
                    addIncorrectBtn.onclick = function() { addIncorrectAnswer(index); };
                    addIncorrectDiv.appendChild(emptyLabel);
                    addIncorrectDiv.appendChild(addIncorrectBtn);
                    container.appendChild(addIncorrectDiv);

                    const typeInput2 = document.createElement("input");
                    typeInput2.type = "hidden";
                    typeInput2.name = "questions[" + index + "].type";
                    typeInput2.value = "multiple-choice";
                    container.appendChild(typeInput2);

                    const deleteBtn2 = document.createElement("button");
                    deleteBtn2.type = "button";
                    deleteBtn2.textContent = "Delete Question";
                    deleteBtn2.onclick = function() { deleteQuestion(this); };
                    container.appendChild(deleteBtn2);
                    break;

                default:
                    h4.textContent = "Unknown Question Type";
                    container.appendChild(h4);
                    break;
            }
        }

        function deleteQuestion(element) {
            const block = element.closest(".question-block");
            if (block) block.remove();
        }

        function addIncorrectAnswer(index) {
            const container = document.getElementById("incorrect-container-" + index);
            const count = container.childElementCount + 1;

            const wrapper = document.createElement("div");
            wrapper.className = "inline-form";

            const label = document.createElement("label");
            label.textContent = "Incorrect Answer " + count + ":";

            const input = document.createElement("input");
            input.type = "text";
            input.name = "questions[" + index + "].wrong" + count;
            input.required = true;

            wrapper.appendChild(label);
            wrapper.appendChild(input);
            container.appendChild(wrapper);
        }
    </script>
</head>
<body>

<h2>Create Quiz</h2>

<form action="SubmitQuizServlet" method="post" onsubmit="return validateForm()">

    <div class="checkbox-group">
        <label><input type="checkbox" name="randomizeOrder"> Randomize Order</label>
        <label><input type="checkbox" name="onePageView"> One Page View</label>
        <label><input type="checkbox" name="immediateFeedback"> Immediate Feedback</label>
        <label><input type="checkbox" name="practiceMode"> Practice Mode</label>
    </div>

    <div class="form-group">
        <label for="quizName">Quiz Name:</label>
        <input type="text" id="quizName" name="Name">
    </div>

    <div class="form-group">
        <label for="quizDescription">Description:</label>
        <textarea id="quizDescription" name="Description"></textarea>
    </div>

    <h3>Questions</h3>
    <div id="questions-container"></div>

    <div id="add-question-controls" class="form-group">
        <label for="questionTypeSelector">Add Question Type:</label>
        <select id="questionTypeSelector">
            <option value="">Select question type</option>
            <option value="question-response">Question-Response</option>
            <option value="picture-response">Picture-Response</option>
            <option value="multiple-choice">Multiple Choice</option>
            <option value="fill-blank">Fill in the Blank</option>
        </select>
        <button type="button" onclick="addQuestion()">Add Question</button>
    </div>

    <button type="submit" class="submit-button">Create Quiz</button>
</form>

</body>
</html>