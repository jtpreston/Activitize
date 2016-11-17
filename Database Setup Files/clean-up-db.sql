-- -----------------------------------------------------
-- Select database `activitizedbtest`
-- -----------------------------------------------------
USE `activitizedbtest`;

-- -----------------------------------------------------
-- Set foreign key checks off temporarily
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS = 0;

-- -----------------------------------------------------
-- Truncate all tables in order to clean up database
-- -----------------------------------------------------
TRUNCATE `activitizedbtest`.`comments`;
TRUNCATE `activitizedbtest`.`events`;
TRUNCATE `activitizedbtest`.`friend_groups`;
TRUNCATE `activitizedbtest`.`friends`;
TRUNCATE `activitizedbtest`.`persistent_logins`;
TRUNCATE `activitizedbtest`.`reactions`;
TRUNCATE `activitizedbtest`.`replies_to_comments`;
TRUNCATE `activitizedbtest`.`subevents`;
TRUNCATE `activitizedbtest`.`users`;
TRUNCATE `activitizedbtest`.`users_has_events`;
TRUNCATE `activitizedbtest`.`users_has_friend_groups`;
TRUNCATE `activitizedbtest`.`users_user_profile`;

-- -----------------------------------------------------
-- Set foreign key checks back on
-- -----------------------------------------------------
SET FOREIGN_KEY_CHECKS = 1;
