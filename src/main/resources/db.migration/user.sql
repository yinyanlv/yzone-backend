CREATE TABLE user (
  id INT(16) PRIMARY KEY AUTO_INCREMENT NOT NULL,
  username VARCHAR(32) NOT NULL,
  password VARCHAR(32) NOT NULL,
  role TINYINT(2) NOT NULL DEFAULT 0,  -- 0: 普通用户; 1: 管理员; 2: 超级管理员;
  state TINYINT(2) NOT NULL DEFAULT 1,  -- 0: 禁用; 1: 正常
  create_time DATETIME NOT NULL,
  update_time DATETIME NOT NULL,
  UNIQUE KEY username (username)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;