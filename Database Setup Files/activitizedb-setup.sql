SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema activitizedbtest
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema activitizedbtest
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `activitizedbtest` DEFAULT CHARACTER SET utf8mb4 ;
USE `activitizedbtest` ;

ALTER DATABASE activitizedbtest CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- -----------------------------------------------------
-- Table `activitizedbtest`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`users` (
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(255) NULL,
  `age` DATE NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` VARCHAR(20) NULL,
  `path_to_profile_picture` VARCHAR(255) NULL,
  `number_of_friends` INT NOT NULL DEFAULT 0,
  `using_facebook` TINYINT(1) NOT NULL,
  `facebook_user_id` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`events` (
  `event_id` BIGINT NOT NULL AUTO_INCREMENT,
  `event_name` VARCHAR(255) NOT NULL,
  `event_start` DATETIME NOT NULL,
  `event_end` DATETIME NULL,
  `description` TEXT NULL,
  `location` VARCHAR(255) NOT NULL,
  `private` TINYINT(1) NOT NULL,
  `number_of_comments` INT NOT NULL DEFAULT 0,
  `path_to_event_picture` VARCHAR(255) NULL,
  `number_going` INT NOT NULL DEFAULT 1,
  `number_not_going` INT NOT NULL DEFAULT 0,
  `subevent` TINYINT(1) NOT NULL,
  PRIMARY KEY (`event_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`subevents`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`subevents` (
  `subevents_id` BIGINT NOT NULL AUTO_INCREMENT,
  `events_event_id` BIGINT NOT NULL,
  `subevents_event_id` BIGINT NOT NULL,
  PRIMARY KEY (`subevents_id`),
  CONSTRAINT `fk_subevents_events1`
    FOREIGN KEY (`events_event_id`)
    REFERENCES `activitizedbtest`.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`users_has_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`users_has_events` (
  `users_user_id` BIGINT NOT NULL,
  `events_event_id` BIGINT NOT NULL,
  `favorite` TINYINT(1) NOT NULL,
  `admin` TINYINT(1) NOT NULL,
  `going` TINYINT(1) NOT NULL,
  PRIMARY KEY (`users_user_id`, `events_event_id`),
  INDEX `fk_users_has_events_events1_idx` (`events_event_id` ASC),
  INDEX `fk_users_has_events_users_idx` (`users_user_id` ASC),
  CONSTRAINT `fk_users_has_events_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `activitizedbtest`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_events_events1`
    FOREIGN KEY (`events_event_id`)
    REFERENCES `activitizedbtest`.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`comments` (
  `comment_id` BIGINT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `events_event_id` BIGINT NOT NULL,
  `number_of_replies` INT NOT NULL DEFAULT 0,
  `yeah` INT NOT NULL DEFAULT 0,
  `nah` INT NOT NULL DEFAULT 0,
  `replies_to_comments_id` BIGINT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comments_events1_idx` (`events_event_id` ASC),
  CONSTRAINT `fk_comments_events1`
    FOREIGN KEY (`events_event_id`)
    REFERENCES `activitizedbtest`.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`replies_to_comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`replies_to_comments` (
  `replies_to_comments_id` BIGINT NOT NULL AUTO_INCREMENT,
  `comments_comment_id` BIGINT NOT NULL,
  PRIMARY KEY (`replies_to_comments_id`),
  INDEX `fk_replies_to_comments_comments1_idx` (`comments_comment_id` ASC),
  CONSTRAINT `fk_replies_to_comments_comments1`
    FOREIGN KEY (`comments_comment_id`)
    REFERENCES `activitizedbtest`.`comments` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`reactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`reactions` (
  `reactions_id` BIGINT NOT NULL AUTO_INCREMENT,
  `comments_comment_id` BIGINT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `yeah` TINYINT(1) NOT NULL,
  PRIMARY KEY (`reactions_id`),
  CONSTRAINT `fk_reactions_comments1`
    FOREIGN KEY (`comments_comment_id`)
    REFERENCES `activitizedbtest`.`comments` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`friends` (
  `friends_id` BIGINT NOT NULL AUTO_INCREMENT,
  `users_user_id` BIGINT NOT NULL,
  `other_user_id` BIGINT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`friends_id`),
  CONSTRAINT `fk_friends_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `activitizedbtest`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`friend_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`friend_groups` (
  `friend_groups_id` BIGINT NOT NULL AUTO_INCREMENT,
  `group_name` VARCHAR(255) NOT NULL,
  `group_size` INT NOT NULL DEFAULT 1,
  `group_owner` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`friend_groups_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`users_has_friend_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`users_has_friend_groups` (
  `users_user_id` BIGINT NOT NULL,
  `friend_groups_friend_group_id` BIGINT NOT NULL,
  PRIMARY KEY (`users_user_id`, `friend_groups_friend_group_id`),
  INDEX `fk_users_has_friend_groups_friend_groups1_idx` (`friend_groups_friend_group_id` ASC),
  INDEX `fk_users_has_friend_groups_users_idx` (`users_user_id` ASC),
  CONSTRAINT `fk_users_has_friend_groups_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `activitizedbtest`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_friend_groups_friend_groups1`
    FOREIGN KEY (`friend_groups_friend_group_id`)
    REFERENCES `activitizedbtest`.`friend_groups` (`friend_groups_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`persistent_logins`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`persistent_logins` (
  `username` VARCHAR(255) NOT NULL,
  `series` VARCHAR(64) NOT NULL,
  `token` VARCHAR(64) NOT NULL,
  `last_used` TIMESTAMP NOT NULL,
  PRIMARY KEY (series))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`user_profile` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(30) NOT NULL,
  PRIMARY KEY (id),
  UNIQUE (type))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `activitizedbtest`.`users_user_profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedbtest`.`users_user_profile` (
  users_user_id BIGINT NOT NULL,
  user_profile_id BIGINT NOT NULL,
  PRIMARY KEY (users_user_id, user_profile_id),
  CONSTRAINT fk_users FOREIGN KEY (users_user_id) REFERENCES users (user_id),
  CONSTRAINT fk_user_profile FOREIGN KEY (user_profile_id) REFERENCES user_profile (id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Populate `activitizedbtest`.`user_profile`
-- -----------------------------------------------------
INSERT INTO user_profile(type)
VALUES ('USER');
INSERT INTO user_profile(type)
VALUES ('ADMIN');
INSERT INTO user_profile(type)
VALUES ('DBA');


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
