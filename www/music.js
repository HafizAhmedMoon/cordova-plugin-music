var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var Music = function () {
};

/**
 * getPlayLists
 *
 * @param {Function} successCallback
 * @param {Function} errorCallback
 * @return {Array} An object of key/value pairs of all Playlists.
 */
Music.getPlaylists = function (success, fail) {
    exec(success, fail, 'Music', 'getPlaylists', []);
};

/**
 * getSongsFromPlaylist
 *
 * @param {Function} successCallback
 * @param {Function} errorCallback
 * @return {Array} An object of key/value pairs of all Songs.
 */
Music.getSongsFromPlaylist = function (id, success, fail) {
    exec(success, fail, 'Music', 'getSongsFromPlaylist', [id]);
};

/**
 * getSongs
 *
 * @param {Function} successCallback
 * @param {Function} errorCallback
 * @return {Array} An object of key/value pairs of all Songs.
 */
Music.getSongs = function (success, fail) {
    exec(success, fail, 'Music', 'getSongs', []);
};

/**
 * playSong
 *
 * @param {Function} successCallback
 * @param {Function} errorCallback
 */
Music.playSong = function (id, success, fail) {
    exec(success, fail, 'Music', 'playSong', [id]);
};
Music.stopSong = function (success, fail) {
    exec(success, fail, 'Music', 'stopSong', []);
};

module.exports = Music;