CREATE DATABASE IF NOT EXISTS quizWebsite;
USE quizWebsite;


CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(256) NOT NULL,
    hash VARCHAR(64) NOT NULL,
    join_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO  Users(id, username, password_hash, hash)
VALUES (1, 'Tini', 'T', 'Tini'),
       (2, 'Luka', 'L', 'Luka'),
       (3, 'Tamari', 'T', 'Tamari');
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
INSERT INTO Quizzes(id, title, description, creator_id, is_random_order, is_one_page, is_immediate_correction, is_practice)
VALUES (1, 'title1', 'description1', 1, true, true, true, true);


CREATE TABLE Questions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    quiz_id INT NOT NULL,
    type ENUM('question_response', 'fill_in_the_blank', 'multiple_choice', 'picture_response_questions') NOT NULL,
    question TEXT NOT NULL,
    position INT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES Quizzes(id) ON DELETE CASCADE
);
INSERT INTO Questions(id, quiz_id, type, question, position)
VALUES (1, 1, 'question_response', 'in which university do you study?', '0');
CREATE TABLE Friends (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    friend_id INT NOT NULL,
    status ENUM('waiting', 'accepted', 'rejected') DEFAULT 'waiting',
    requested_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (friend_id) REFERENCES Users(id) ON DELETE CASCADE,
    UNIQUE(user_id, friend_id)
);

CREATE TABLE Answers (
    id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL,
    text TEXT NOT NULL,
    is_ordered BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES Questions(id) ON DELETE CASCADE
);
INSERT INTO Answers(id, question_id, text, is_ordered)
values (1, 1, 'freeuni', false);

CREATE TABLE Attempts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    quiz_id INT NOT NULL,
    score DOUBLE NOT NULL,
    duration INT NOT NULL,
    taken TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES Quizzes(id) ON DELETE CASCADE
);

CREATE TABLE Messages (
    id INT AUTO_INCREMENT PRIMARY KEY,
    sender_id INT NOT NULL,
    receiver_id INT NOT NULL,
    type ENUM('friend_request', 'challenge', 'text') NOT NULL,
    message TEXT,
    quiz_id INT DEFAULT NULL,
    sent_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (sender_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (receiver_id) REFERENCES Users(id) ON DELETE CASCADE,
    FOREIGN KEY (quiz_id) REFERENCES Quizzes(id) ON DELETE SET NULL
);

