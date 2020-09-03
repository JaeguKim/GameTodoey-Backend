CREATE DEFINER=`hbstudent`@`localhost` TRIGGER `game_user_AFTER_INSERT` AFTER INSERT ON `game_user` FOR EACH ROW BEGIN
	DECLARE	gameId INT;
	SET gameId = NEW.game_id;
	UPDATE game SET popularity = popularity+1 WHERE game.id = gameId;
END