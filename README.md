# ServerStatus
Small BungeeCord plugin to check the status of connected servers and block players from attempting to connect to offline servers. Builds can be found on the [Minebench.de Jenkins](http://ci.minebench.de/job/ServerStatus/).

### Commands:
/serverstatus                         | (Aliases: /ss and /status)
------------------------------------- | -----------------------------------
/serverstatus                         | Show a list with all the statuses
/serverstatus reload                  | Reloads the config
/serverstatus refresh                 | Run a manual status refresh
/serverstatus setonline <servername>  | Manually sets the server's status to online
/serverstatus setoffline <servername> | Manually sets the server's status to offline

The setoffline option will stop the plugin from checking the status of the server until it's set back to online via the command!

### Permissions:
Name                              | Description
--------------------------------- | --------------------------------------
ServerStatus.command              | Access to the command and all its options
ServerStatus.info                 | Receive information messages about when a server is automatically detected as online or offline
ServerStatus.preventswitch.bypass | Bypass the switch-to-offline-server-prevention, usefull for when you manually marked a as server for maintance

### Config:
```yaml
# Checkintervall in seconds
checkinterval:
  online: 10
  offline: 30
messages:
  preventswitch: "&cYou can't join &e%server%&c! The server is offline!"
  info:
    online: "&e%server% &ais now online!"
    offline: "&e%server% &cis now offline!"
```

### License:
All Server* classes are licensed under the AGPLv3:

```
 ServerStatus
 Copyright (C) 2021 Max Lee aka Phoenix616 (max@themoep.de)

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program.  If not, see <https://www.gnu.org/licenses/>.
```

The FileConfiguration class is licensed under the [Nietzsche Public License v0.6](http://copyfree.org/content/standard/licenses/nietzschepl/license.txt)
