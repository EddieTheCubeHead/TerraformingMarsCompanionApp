# TerraformingMarsCompanionApp
An app for helping players maintain their resources when playing the board game terraforming mars.

This is an app that started developed as the practice assignment for our Object-Oriented Programming course by EddieTheCubeHead, Flankmain,
Fyllin and ilupoS and has since been continued as a hobby project by Eddie. The aim of the app is not to replace the physical board game,
but to quicken the experience as well as make resource management much more convinient and reduce mistakes caused by lack of attention. The
app has a server solution Eddie is developing with it. The repository can be found here: https://github.com/EddieTheCubeHead/TFMCA_server

The app aims to deliver four levels of assistance depending on player preference:

- The lowest level has only the resource listing and an opportunity to update resource amounts and production amounts as well as a button
to gain productions after a generation.

- The second lowest level is an automated single user mode, where manual resource management is still needed for interaction with other
players and managing resources on cards, but the player can play cards via a search field and the card benefits are applied automatically.

- The two highest levels offer identical amounts of assistance, but different amounts of convinience. The first of them is a hot-seat mode
where players pass the phone around the table and basically all resource management, including player interaction, is automated while the
highest level is up to five players connecting to a shared game via a server and getting the aforementioned benefits.

The current development stage of the app is "kinda functional". In theory you could play a game without any expansions while using the app
as a helper, hotseat or via a server. There are however problems such as poor login system, no error handling in server play, bugged edge
cases, some really poor user interfaces etc. Development at the moment is mostly about fixing poor choices made due to lack of time in the
assignment process and slowly fixing the aforementioned probelms while doing it.

To see a more clear roadmap of the planned development process check the TODO file in the main app folder 
(/app/src/main/java/com/example/terraformingmarscompanionapp/TODO)
