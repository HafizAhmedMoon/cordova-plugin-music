cordova.define("com.cordova.music.music", function(require, exports, module) {
var argscheck = require('cordova/argscheck'),
    utils = require('cordova/utils'),
    exec = require('cordova/exec');

var Music = function () {
};
var currentCallback=null;

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
 * @param {int} id
 * @param {Function} successCallback
 * @param {Function} errorCallback
 */
Music.playSong = function (id, success, fail) {
    currentCallback=success;
    exec(null, fail, 'Music', 'playSong', [id]);
};
Music.stopSong = function (success, fail) {
    exec(success, fail, 'Music', 'stopSong', []);
};

module.exports = Music;

function onMessageFromNative(msg) {
    if (msg) {
        currentCallback(msg)
    }
}
if (cordova.platformId === 'android' || cordova.platformId === 'amazon-fireos' || cordova.platformId === 'windowsphone') {

    var channel = require('cordova/channel');

    channel.createSticky('onMusicPluginReady');
    channel.waitForInitialization('onMusicPluginReady');

    channel.onCordovaReady.subscribe(function() {
        exec(onMessageFromNative, undefined, 'Music', 'messageChannel', []);
        channel.initializationComplete('onMusicPluginReady');
    });
}
});
