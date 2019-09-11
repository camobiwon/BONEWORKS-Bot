# BONEWORKS Bot
Bot for the BONEWORKS fan Discord server

**Instructions to get the bot running**
- Create an application on [Discord's developer page](https://discordapp.com/developers/applications), and add a bot for that application
- Add the bot to a server you own on Discord
- Download the project, and make sure you import all required Gradle plugins
- Add your bot's token into a file titled "token.txt", which you then put into src\main\resources
- You can also add yours or others IDs to src\main\resources\admins.txt, just separate each with a comma

- Create a new package in org.camobiwon.boneworksbot titled "secret"
- Add 2 new classes to this package, one called "SecretCommands" the other called "UserJoin"
- In SecretCommands, add "implements MessageCreateListener" and have the IDE add methods
- In UserJoin, add "implements ServerMemberJoinEvent" and have the IDE add methods
- You should now be able to run the project and your bot should go online