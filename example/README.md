Music Plugin Example
===================

First make sure you have installed NPM(Node Pakage Manager) if you have not, install from here https://nodejs.org

### To Setup

Install cordova, ionic and bower packages if you have already installed ignore it
```
npm install cordova ionic bower -g
```

Now install ionic lib from bower
```
bower install
```

### Prepare to Build

Now install platform and plugins just type
```
ionic state restore
```
Platforms and Plugins mentioned in package.json

### To Build and Run

For android
```
ionic run android
```

For IOS
```
ionic run ios
```

Note: For more info [Ionic CLI docs](http://ionicframework.com/docs/cli/)