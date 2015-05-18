#import <Cordova/CDVPlugin.h>
#import <Cordova/CDV.h>
#import <MediaPlayer/MPMediaQuery.h>
#import <MediaPlayer/MPMediaPlaylist.h>


@interface Music : CDVPlugin

- (void) getPlaylists:(CDVInvokedUrlCommand*)command;

- (void) getSongs:(CDVInvokedUrlCommand*)command;

- (void) getSongsFromPlaylist:(CDVInvokedUrlCommand*)command;

@end