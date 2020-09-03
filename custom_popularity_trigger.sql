DROP TRIGGER IF EXISTS updatePopularity;

CREATE TRIGGER updatePopularity
AFTER INSERT ON game_user
FOR EACH ROW
BEGIN
	DECLARE	gameId INT;
	SET gameId = NEW.game_user.id;
	UPDATE game SET popularity = popularity+1 WHERE game.id = gameId;
END $$
