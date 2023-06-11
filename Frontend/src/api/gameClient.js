import BaseClass from "../util/baseClass";
import axios from 'axios'

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
 */
export default class GameClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'createGame', 'getListOfGames'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Gets the concert for the given ID.
     * @param title
     * @param loanerName
     * @param borrowerName
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */

    async createGame(title, loanerName, borrowerName, errorCallback) {
        try {
            const response = await this.client.post(`/game`, {
                "title": title,
                "loanerName": loanerName,
                "borrowerName": borrowerName
            });
            return response.data;
        } catch (error) {
            this.handleError("createGame", error, errorCallback);
        }
    }

    async getListOfGames(errorCallback) {
        try {
            const response = await this.client.get(`/game/listOfGames`);
            return response.data;
        } catch (error) {
            this.handleError("listOfGames", error, errorCallback);
        }
    }

    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
