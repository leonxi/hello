var exec = require('cordova/exec');

var RabbitMQPlugin = function() {};

RabbitMQPlugin.prototype.isPlatformIOS = function() {
  return (
    device.platform === "iPhone" ||
    device.platform === "iPad" ||
    device.platform === "iPod touch" ||
    device.platform === "iOS"
  );
};

RabbitMQPlugin.prototype.errorCallback = function(msg) {
  console.log("RabbitMQPlugin Callback Error: " + msg);
};

RabbitMQPlugin.prototype.callNative = function(
  name,
  args,
  successCallback,
  errorCallback
) {
  if (errorCallback) {
    cordova.exec(successCallback, errorCallback, "RabbitMQPlugin", name, args);
  } else {
    cordova.exec(
      successCallback,
      this.errorCallback,
      "RabbitMQPlugin",
      name,
      args
    );
  }
};

RabbitMQPlugin.prototype.init = function() {
  if (this.isPlatformIOS()) {
    this.callNative("initial", [], null);
  } else {
    this.callNative("init", [], null);
  }
};

// Common methods
RabbitMQPlugin.prototype.init = function() {
  if (this.isPlatformIOS()) {
    this.callNative("initial", [], null);
  } else {
    this.callNative("init", [], null);
  }
};

if (!window.plugins) {
  window.plugins = {};
}

if (!window.plugins.RabbitMQPlugin) {
  window.plugins.RabbitMQPlugin = new RabbitMQPlugin();
}

module.exports = new RabbitMQPlugin();
