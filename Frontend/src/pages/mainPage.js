import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import GameClient from "../api/gameClient";

/**
 * Logic needed for the view playlist page of the website.
 */
class MainPage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGet','onCreateGame', 'renderGames'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the concert list.
     */
    async mount() {
        document.getElementById('create-game-form').addEventListener('submit', this.onCreateGame);
        this.client = new GameClient();
        this.dataStore.addChangeListener(this.renderGames);
        await this.client.getListOfGames();

    }

    // Render Methods --------------------------------------------------------------------------------------------------
    async renderGames() {
        let resultArea = document.getElementById("games-displayed");

        let games = this.dataStore.get("games");

        let html = "<ul>";

        if (games) {
            for(let game of games){
                html += `<li><h3>${game.title}</h3><h4>${game.loaner}</h4><p>${game.borrower}</p></li>`;
            }
            resultArea.innerHTML = html;

        } else {
            resultArea.innerHTML = "No games added";
        }
    }

    async onGet() {
        let result = await this.client.getListOfGames(this.errorHandler);
        this.dataStore.set("games", result);

        if (result) {
            console.log(`Got games!`)
        } else {
            console.log("Error doing GET!  Try again...");
        }
    }

    async onGetGames() {
        let result = await this.client.getListOfGames(this.errorHandler);
        this.dataStore.set("games", result);
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onCreateGame(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        let title = document.getElementById("create-game-title").value;
        let loaner = document.getElementById("create-game-loaner").value;
        let borrower = document.getElementById("create-game-borrower").value;

        const createdGame = await this.client.createGame(title, loaner, borrower, this.errorHandler);

        if (createdGame) {
            this.showMessage(`Game has been created!`);
            await this.onGetGames();
        } else {
            this.errorHandler("Error creating!  Try again...");
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const mainPage = new MainPage();
    await mainPage.mount();
};

window.addEventListener('DOMContentLoaded', main);
