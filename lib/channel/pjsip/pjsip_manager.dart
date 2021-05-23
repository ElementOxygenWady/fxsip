import 'package:flutter/services.dart';
import 'package:fxsip/channel/pjsip/constants.dart';

class PjsipManager {
  static final _instance = PjsipManager._internal();
  static const MethodChannel _channel = MethodChannel(Constants.CHANNEL);

  PjsipManager._internal() {
    //Wady: TODO, Implement logic for initializing something else
  }

  factory PjsipManager() {
    return _instance;
  }

  static PjsipManager get instance => _instance;

  Future<bool> init() async {
    return await _channel.invokeMethod(Constants.METHOD_INIT);
  }
}
