-- uplift.category definition

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-- uplift.hibernate_sequence definition

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
);


-- uplift.set_work definition

CREATE TABLE `set_work` (
  `id` bigint NOT NULL,
  `kind_work` varchar(255) DEFAULT NULL,
  `reps` int DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-- uplift.workout definition

CREATE TABLE `workout` (
  `id` bigint NOT NULL,
  `categories` varchar(255) DEFAULT NULL,
  `date_creation` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


-- uplift.exercise definition

CREATE TABLE `exercise` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_exercise_category` (`category_id`),
  CONSTRAINT `FK_exercise_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`)
);


-- uplift.exercise_sets definition

CREATE TABLE `exercise_sets` (
  `exercise_id` bigint NOT NULL,
  `sets_id` bigint NOT NULL,
  UNIQUE KEY `UK_exercise_sets_sets_id` (`sets_id`),
  KEY `FK_exercise_sets_exercise_id` (`exercise_id`),
  CONSTRAINT `FK_exercise_sets_exercise_id` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
  CONSTRAINT `FK_exercise_sets_sets_id` FOREIGN KEY (`sets_id`) REFERENCES `set_work` (`id`)
);


-- uplift.workout_exercises definition

CREATE TABLE `workout_exercises` (
  `workout_id` bigint NOT NULL,
  `exercise_id` bigint NOT NULL,
  KEY `FK_workout_exercises_exercise_id` (`exercise_id`),
  KEY `FK_workout_exercises_workout_id` (`workout_id`),
  CONSTRAINT `FK_workout_exercises_exercise_id` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`),
  CONSTRAINT `FK_workout_exercises_workout_id` FOREIGN KEY (`workout_id`) REFERENCES `workout` (`id`)
);
