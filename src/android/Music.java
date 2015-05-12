package com.cordova.music;

import android.content.ContentResolver;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Music  extends CordovaPlugin {

    MediaPlayer player= new MediaPlayer();
    /**
     * Executes the request and returns PluginResult.
     *
     * @param action            The action to execute.
     * @param args              JSONArry of arguments for the plugin.
     * @param callbackContext   The callback id used when calling back into JavaScript.
     * @return                  True if the action was valid, false if not.
     */
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("getPlaylists")) {
            ContentResolver contentResolver =this.cordova.getActivity().getContentResolver();
            String[] proj = {"*"};
            Uri tempPlaylistURI = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
            final String idKey = MediaStore.Audio.Playlists._ID;
            final String nameKey = MediaStore.Audio.Playlists.NAME;

            Cursor playListCursor= contentResolver.query(tempPlaylistURI, proj, null, null, null);

            if(playListCursor == null){
                return false;
            }

            String playListName = null;
            String playListId = null;
            JSONArray res = new JSONArray();

            for(int i = 0; i <playListCursor.getCount() ; i++)
            {
                playListCursor.moveToPosition(i);
                playListId = playListCursor.getString(playListCursor.getColumnIndex(idKey));
                playListName = playListCursor.getString(playListCursor.getColumnIndex(nameKey));
                JSONObject r = new JSONObject();
                r.put("id",playListId);
                r.put("name",playListName);
                res.put(i,r);
            }
            if(playListCursor != null)
                playListCursor.close();
            callbackContext.success(res);
            return true;
        }
        else if (action.equals("getSongsFromPlaylist")) {
            ContentResolver contentResolver =this.cordova.getActivity().getContentResolver();
            Long playListID = args.getLong(0);
            String[] proj = {"*"};
            Uri psUri = MediaStore.Audio.Playlists.Members.getContentUri("external", playListID);
            final String psIdKey = MediaStore.Audio.Playlists.Members._ID;
            final String psTitleKey = MediaStore.Audio.Playlists.Members.TITLE;
            final String psArtistKey = MediaStore.Audio.Playlists.Members.ARTIST;

            Cursor psCursor = contentResolver.query(psUri, proj, null, null, null);

            if(psCursor == null){
                return false;
            }

            JSONArray psRes = new JSONArray();

            for(int i = 0; i < psCursor.getCount() ; i++)
            {
                psCursor.moveToPosition(i);
                JSONObject r = new JSONObject();
                r.put("id", psCursor.getString((psCursor.getColumnIndex(psIdKey))));
                r.put("title", psCursor.getString((psCursor.getColumnIndex(psTitleKey))));
                r.put("artist", psCursor.getString((psCursor.getColumnIndex(psArtistKey))));
                psRes.put(i,r);
            }
            if(psCursor != null)
                psCursor.close();
            callbackContext.success(psRes);
            return true;
        }
        else if (action.equals("getSongs")) {
            ContentResolver contentResolver =this.cordova.getActivity().getContentResolver();
            String[] proj = {"*"};
            Uri psUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            final String psIdKey = MediaStore.Audio.Media._ID;
            final String psTitleKey = MediaStore.Audio.Media.TITLE;
            final String psArtistKey = MediaStore.Audio.Media.ARTIST;

            Cursor psCursor = contentResolver.query(psUri, proj, null, null, null);

            if(psCursor == null){
                return false;
            }

            JSONArray psRes = new JSONArray();

            for(int i = 0; i < psCursor.getCount() ; i++)
            {
                psCursor.moveToPosition(i);
                JSONObject r = new JSONObject();
                r.put("id", psCursor.getString((psCursor.getColumnIndex(psIdKey))));
                r.put("title", psCursor.getString((psCursor.getColumnIndex(psTitleKey))));
                r.put("artist", psCursor.getString((psCursor.getColumnIndex(psArtistKey))));
                System.out.println(psCursor.getString((psCursor.getColumnIndex(MediaStore.Audio.Media.DATA))));
                psRes.put(i,r);
            }
            if(psCursor != null)
                psCursor.close();
            callbackContext.success(psRes);
            return true;
        }
        else if (action.equals("playSong")) {
            ContentResolver contentResolver =this.cordova.getActivity().getContentResolver();
            String songID = args.getString(0);
            String[] proj = {"*"};
            Uri psUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            final String sIdKey = MediaStore.Audio.Media._ID;
            final String sDataKey = MediaStore.Audio.Media.DATA;
            final String sTitleKey = MediaStore.Audio.Media.TITLE;
            final String selection=  MediaStore.Audio.Media._ID + " == "+songID;;

            Cursor sCurosr = contentResolver.query(psUri, proj, selection, null, null);

            if(sCurosr == null){
                return false;
            }
            sCurosr.moveToPosition(0);
            final Uri sUri= Uri.parse(sCurosr.getString(sCurosr.getColumnIndex(sDataKey)));
            if (sUri == null) {
                System.out.println("Called playAudio with null data stream.");
                return false;
            }
            try {
                player.setDataSource(this.cordova.getActivity().getApplicationContext(), sUri);
                player.prepare();
                player.start();
            } catch (Exception e) {
                System.out.println("Failed to start MediaPlayer: " + e.getMessage());
                return false;
            }
            if(sCurosr!= null)
                sCurosr.close();
            callbackContext.success();
            return true;
        }
        else if (action.equals("stopSong")) {
            try {
                if(player.isPlaying()){
                    player.pause();
                    player.seekTo(0);
                    /*player.release();
                    player=null;*/
                }
            } catch (Exception e) {
                System.out.println("Failed to stop MediaPlayer: " + e.getMessage());
                return false;
            }
            callbackContext.success();
            return true;
        }
        else {
            return false;
        }
    }

}