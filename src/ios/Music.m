#import "Music.h"

@implementation Music

- (void)getPlaylists:(CDVInvokedUrlCommand*)command
{
    
    //getAllPlaylists
    MPMediaQuery *myPlaylistsQuery = [MPMediaQuery playlistsQuery];
    NSArray *playlists = [myPlaylistsQuery collections];
    NSMutableArray* returnPlaylits = [NSMutableArray arrayWithCapacity:1];
    
    for (MPMediaPlaylist *playlist in playlists) {
        NSString *playListId = [NSString stringWithFormat:@"%@", [playlist valueForProperty: MPMediaPlaylistPropertyPersistentID]];
        NSString *playListName = [NSString stringWithFormat:@"%@", [playlist valueForProperty: MPMediaPlaylistPropertyName]];
        NSMutableDictionary* po = [NSMutableDictionary dictionaryWithCapacity:1];
        [po setObject: playListName forKey:@"name"];
        [po setObject: playListId forKey:@"id"];
        [returnPlaylits addObject:po];
    }
    NSLog(@"%@", returnPlaylits);
    
    CDVPluginResult* pluginResult = [CDVPluginResult
                                     resultWithStatus:CDVCommandStatus_OK
                                     messageAsArray:returnPlaylits];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getSongs:(CDVInvokedUrlCommand*)command
{
    
    //getAllSongs
    MPMediaQuery *mySongsQuery = [MPMediaQuery songsQuery];
    NSArray *songs = [mySongsQuery items];
    NSMutableArray* songsArray = [NSMutableArray arrayWithCapacity:1];
    
    for (MPMediaItem *song in songs) {
        NSString *songtId = [song valueForProperty: MPMediaItemPropertyPersistentID];
        NSString *songName = [song valueForProperty: MPMediaItemPropertyTitle];
        
        NSMutableDictionary* so = [NSMutableDictionary dictionaryWithCapacity:1];
        
        [so setObject: songName forKey:@"name"];
        [so setObject: songtId forKey:@"id"];
        
        [songsArray addObject:so];
    }
    NSLog(@"%@", songsArray);
    
    CDVPluginResult* pluginResult = [CDVPluginResult
                                     resultWithStatus:CDVCommandStatus_OK
                                     messageAsArray:songsArray];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

- (void)getSongsFromPlaylist:(CDVInvokedUrlCommand*)command
{
    
    //getPlaylistById
    NSString *playlistId = [command.arguments objectAtIndex:0];
    NSLog(@"PlaylistId: %@", playlistId);
    
    MPMediaPropertyPredicate *playlistIdPredicate =
    [MPMediaPropertyPredicate predicateWithValue:playlistId
                                     forProperty:MPMediaItemPropertyPersistentID];
    
    MPMediaQuery *myPlaylistsQuery = [MPMediaQuery playlistsQuery];
    [myPlaylistsQuery addFilterPredicate:playlistIdPredicate];
    
    NSArray *playlists = [myPlaylistsQuery collections];
    NSMutableArray* songsArray = [NSMutableArray arrayWithCapacity:1];
    
    NSString *playlistName;
        if (playlists.count > 0)
        {
            //song exists
            MPMediaPlaylist *playlist = [playlists objectAtIndex:0];
            NSArray *songs = [playlist items];
            
            if (songs.count > 0) {
                
                NSLog(@"Songs Availabe");
                
                for (MPMediaItem *song in songs) {
                    NSString *songtId = [song valueForProperty: MPMediaItemPropertyPersistentID];
                    NSString *songName = [song valueForProperty: MPMediaItemPropertyTitle];
                    
                    NSMutableDictionary* so = [NSMutableDictionary dictionaryWithCapacity:1];
                    
                    [so setObject: songName forKey:@"name"];
                    [so setObject: songtId forKey:@"id"];
                    
                    [songsArray addObject:so];
                }
            }
            playlistName= [playlist valueForProperty: MPMediaPlaylistPropertyName];
            NSLog(@"%@", playlistName);
        }
    
    CDVPluginResult* pluginResult = [CDVPluginResult
                                     resultWithStatus:CDVCommandStatus_OK
                                     messageAsArray:songsArray];
    
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}


@end