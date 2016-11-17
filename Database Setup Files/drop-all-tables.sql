-- -----------------------------------------------------
-- Select database `activitizedbtest`
-- -----------------------------------------------------
USE `activitizedbtest`;

-- -----------------------------------------------------
-- Set foreign key checks off temporarily
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------
-- Drop all tables in order to upgrade database schema
-- -----------------------------------------------------
DROP TABLE IF EXISTS `activitizedbtest`.`comments`;
DROP TABLE IF EXISTS `activitizedbtest`.`events`;
DROP TABLE IF EXISTS `activitizedbtest`.`friend_groups`;
DROP TABLE IF EXISTS `activitizedbtest`.`friends`;
DROP TABLE IF EXISTS `activitizedbtest`.`persistent_logins`;
DROP TABLE IF EXISTS `activitizedbtest`.`reactions`;
DROP TABLE IF EXISTS `activitizedbtest`.`replies_to_comments`;
DROP TABLE IF EXISTS `activitizedbtest`.`subevents`;
DROP TABLE IF EXISTS `activitizedbtest`.`user_profile`;
DROP TABLE IF EXISTS `activitizedbtest`.`users`;
DROP TABLE IF EXISTS `activitizedbtest`.`users_has_events`;
DROP TABLE IF EXISTS `activitizedbtest`.`users_has_friend_groups`;
DROP TABLE IF EXISTS `activitizedbtest`.`users_user_profile`;

-- -----------------------------------------------------
-- Set foreign key checks back on
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS = 1;
