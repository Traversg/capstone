import FiveLiftsClient from '../api/fiveLiftsClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the starting weights page of the website.
 */
class StartingWeights extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewPlaylist);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('starting-weights-button').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new FiveLiftsClient();
    }

    /**
     * Method to run when the create profile submit button is pressed. Call the FiveLiftsService to create the
     * profile.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('starting-weights-button');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const squat = document.getElementById('squat').value;
        const benchPress = document.getElementById('bench-press').value;
        const overheadPress = document.getElementById('overhead-press').value;
        const barbellRow = document.getElementById('barbell-row').value;
        const deadlift = document.getElementById('deadlift').value;
        const bodyWeight = document.getElementById('body-weight').value;

        const profile = await this.client.createPlaylist(playlistName, tags, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('playlist', playlist);
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewPlaylist() {
        const playlist = this.dataStore.get('playlist');
        if (playlist != null) {
            window.location.href = `/playlist.html?id=${playlist.id}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const startingWeights = new StartingWeights();
    startingWeights.mount();
};

window.addEventListener('DOMContentLoaded', main);