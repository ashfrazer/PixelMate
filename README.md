<h1 align="center">
  Welcome to PixelMate!
</h1>

<p align="center">
  <img src="src/main/java/edu/uca/swe/Icons/king_black.png">
  <img src="src/main/java/edu/uca/swe/Icons/queen_black.png">
  <img src="src/main/java/edu/uca/swe/Icons/bishop_black.png">
  <img src="src/main/java/edu/uca/swe/Icons/rook_black.png">
  <img src="src/main/java/edu/uca/swe/Icons/knight_black.png">
  <img src="src/main/java/edu/uca/swe/Icons/pawn_black.png">
</p>

<p align="center">
  <img src="src/main/java/edu/uca/swe/Icons/king_white.png">
  <img src="src/main/java/edu/uca/swe/Icons/queen_white.png">
  <img src="src/main/java/edu/uca/swe/Icons/bishop_white.png">
  <img src="src/main/java/edu/uca/swe/Icons/rook_white.png">
  <img src="src/main/java/edu/uca/swe/Icons/knight_white.png">
  <img src="src/main/java/edu/uca/swe/Icons/pawn_white.png">
</p>

## Instructions to Run (current state)

**Make sure to have [Docker](https://www.docker.com/get-started/) installed. Docker will containerize the MariaDB database.**

- Open **three** Command Prompt windows, navigate to the project directory, and run the following command in all 3:
`mvnw clean install`

- In Window 1, run the following command to run the back-end server:
`mvnw exec:java -Dexec.mainClass="edu.uca.swe.Game.Connection.Server"`

- In Windows 2 & 3, run the following command in both to open 2 instances of PixelMate.
`mvnw exec:java -Dexec.mainClass="edu.uca.swe.GUI.MainGUI"`

- In Window 2, log in to an account of your choice and select *Host*.

- In Window 3, log in to the account *test* (password=1234) and select *Client*.

The two players should be connected, and when the Host presses start, the chess board should be displayed.
