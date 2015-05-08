cordova.define("com.cordova.music.music", function(require, exports, module) {

var argscheck = require('cordova/argscheck'),
    channel = require('cordova/channel'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec'),
    cordova = require('cordova');

    var Music = function() {
    };

    /**
     * getPlayLists
     *
     * @param {Function} successCallback
     * @param {Function} errorCallback
     * @return {Array} An object of key/value pairs of all playlists.
     */
     Music.getPlaylists = function(success, fail) {
        exec(success, fail, 'Music', 'getPlaylists', []);
     };

    /**
     * getSongsFromPlaylist
     *
     * @param {Function} successCallback
     * @param {Function} errorCallback
     * @return {Array} An object of key/value pairs of all playlists.
     */
     Music.getSongsFromPlaylist = function(id, success, fail) {
        exec(success, fail, 'Music', 'getSongsFromPlaylist', [id]);
     };

    /**
     * getAllSongs
     *
     * @param {Function} successCallback
     * @param {Function} errorCallback
     * @return {Array} An object of key/value pairs of all playlists.
     */
     Music.getAllSongs = function(success, fail) {
        exec(success, fail, 'Music', 'getAllSongs', []);
     };

    /**
     * playSong
     *
     * @param {Function} successCallback
     * @param {Function} errorCallback
     * @return {Array} An object of key/value pairs of all playlists.
     */
     Music.playSong = function(id, success, fail) {
        exec(success, fail, 'Music', 'playSong', [id]);
     };
     Music.stopSong = function(success, fail) {
        exec(success, fail, 'Music', 'stopSong', []);
     };

    module.exports = Music;
});