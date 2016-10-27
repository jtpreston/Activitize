SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema activitizedb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema activitizedb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `activitizedb` DEFAULT CHARACTER SET utf8mb4 ;
USE `activitizedb` ;

-- -----------------------------------------------------
-- Table `activitizedb`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`users` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `first_name` VARCHAR(255) NOT NULL,
  `last_name` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(255) NULL,
  `age` DATE NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `phone_number` INT NULL,
  `path_to_profile_picture` VARCHAR(255) NULL,
  `number_of_friends` INT NOT NULL DEFAULT 0,
  `using_facebook` TINYINT(1) NOT NULL,
  `facebook_user_id` BIGINT(20) UNSIGNED NULL,
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`events` (
  `event_id` INT NOT NULL AUTO_INCREMENT,
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
  `subevent_parent_id` INT NULL,
  PRIMARY KEY (`event_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`users_has_events`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`users_has_events` (
  `users_user_id` INT NOT NULL,
  `events_event_id` INT NOT NULL,
  `favorite` TINYINT(1) NOT NULL,
  `admin` TINYINT(1) NOT NULL,
  `going` TINYINT(1) NOT NULL,
  PRIMARY KEY (`users_user_id`, `events_event_id`),
  INDEX `fk_users_has_events_events1_idx` (`events_event_id` ASC),
  INDEX `fk_users_has_events_users_idx` (`users_user_id` ASC),
  CONSTRAINT `fk_users_has_events_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `activitizedb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_events_events1`
    FOREIGN KEY (`events_event_id`)
    REFERENCES `activitizedb`.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`comments` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment` TEXT NOT NULL,
  `timestamp` TIMESTAMP NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `events_event_id` INT NOT NULL,
  `number_of_replies` INT NOT NULL DEFAULT 0,
  `yeah` INT NOT NULL DEFAULT 0,
  `nah` INT NOT NULL DEFAULT 0,
  `replies_to_comments_id` INT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comments_events1_idx` (`events_event_id` ASC),
  CONSTRAINT `fk_comments_events1`
    FOREIGN KEY (`events_event_id`)
    REFERENCES `activitizedb`.`events` (`event_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`replies_to_comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`replies_to_comments` (
  `replies_to_comments_id` INT NOT NULL AUTO_INCREMENT,
  `comments_comment_id` INT NOT NULL,
  PRIMARY KEY (`replies_to_comments_id`),
  INDEX `fk_replies_to_comments_comments1_idx` (`comments_comment_id` ASC),
  CONSTRAINT `fk_replies_to_comments_comments1`
    FOREIGN KEY (`comments_comment_id`)
    REFERENCES `activitizedb`.`comments` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`reactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`reactions` (
  `reactions_id` INT NOT NULL AUTO_INCREMENT,
  `comments_comment_id` INT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `yeah` TINYINT(1) NOT NULL,
  PRIMARY KEY (`reactions_id`),
  CONSTRAINT `fk_reactions_comments1`
    FOREIGN KEY (`comments_comment_id`)
    REFERENCES `activitizedb`.`comments` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`friends`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`friends` (
  `friends_id` INT NOT NULL AUTO_INCREMENT,
  `users_user_id` INT NOT NULL,
  `other_user_id` INT NOT NULL,
  `status` TINYINT(1) NOT NULL,
  PRIMARY KEY (`friends_id`),
  CONSTRAINT `fk_friends_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `activitizedb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


-- -----------------------------------------------------
-- Table `activitizedb`.`friend_groups`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `activitizedb`.`friend_groups` (
  `friend_groups_id` INT NOT NULL AUTO_INCREMENT,
  `users_user_id` INT NOT NULL,
  `group_name` VARCHAR(255) NOT NULL,
  `group_size` INT NOT NULL DEFAULT 1,
  `group_owner` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`friend_groups_id`),
  CONSTRAINT `fk_friend_groups_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `activitizedb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
