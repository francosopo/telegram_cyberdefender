# Telegram cyberdefender signing up bot

This bot is made using Java 17 and maven 3.9.5

It uses Strategy design pattern for making the commands

It does not use Command design pattern.

## Building

Write a config.properties file configuration like this

```.env
BOT_TOKEN=your-secret-bot-token
RECORD_RESPONSIBLES_URI=http://the-url-to-record-responsibles.com
```

Then run
```bash
mvn dependency:resolve 
mvn clean package
```

## Running
```bash
java -jar Telegram_cyberdefender...-with-dependencies.jar
```

See the bot in action, send /start and see the help displayed in the screen.

