CREATE DATABASE IF NOT EXISTS quizWebsite;
USE quizWebsite;


CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(256) NOT NULL,
    hash VARCHAR(64) NOT NULL,
    join_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Quizzes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    description TEXT,
    creator_id INT NOT NULL,
    is_random_order BOOLEAN DEFAULT FALSE,
    is_one_page BOOLEAN DEFAULT TRUE,
    is_immediate_correction BOOLEAN DEFAULT FALSE,
    is_practice BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (creator_id) REFERENCES Users(id) ON DELETE CASCADE
);


CREATE TABLE Questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    type ENUM('question_response', 'fill_in_the_blank', 'multiple_choice', 'picture_response_questions') NOT NULL,
    question TEXT NOT NULL,
    position INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES Quizzes(id) ON DELETE CASCADE
);