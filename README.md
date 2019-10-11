# WorldDownloader-Fabric

## Setup
1. Download the following:
[mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4.zip](https://github.com/Pokechu22/WorldDownloader/releases/download/v4.0.5.3/mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4.zip)
[tiny-remapper-0.2.0.45-fat.jar](https://maven.fabricmc.net/net/fabricmc/tiny-remapper/0.2.0.45/tiny-remapper-0.2.0.45-fat.jar)
[yarn-1.14.4+build.12-tiny.gz](https://maven.fabricmc.net/net/fabricmc/yarn/1.14.4%2Bbuild.12/yarn-1.14.4%2Bbuild.12-tiny.gz)
2. Rename `mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4.zip` to `mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4.jar`
3. Run the following command
```
gradlew
```
4. Grab `minecraft-1.14.4-intermediary-net.fabricmc.yarn.jar` and `minecraft-1.14.4-mapped-net.fabricmc.yarn-12.jar` from `%USERPROFILE%/.gradle/caches/fabric-loom`
5. Run more commands
```
java -jar tiny-remapper-0.2.0.45-fat.jar mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4.jar libs/mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4-dev.jar yarn-1.14.4+build.12-tiny.gz official named minecraft-1.14.4-mapped-net.fabricmc.yarn-12.jar
java -jar tiny-remapper-0.2.0.45-fat.jar mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4.jar mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4-intermediary.jar yarn-1.14.4+build.12-tiny.gz official intermediary minecraft-1.14.4-intermediary-net.fabricmc.yarn.jar
gradlew build
```
6. Copy everything except for `net/minecraft` from the `mod-worlddownloader-baseedit-4.0.5.3-mc1.14.4-intermediary.jar` and add it into `build/libs/mod-fabricwdl-4.0.5.3-mc1.14.4.jar`

Congrats it should hopefully be working.
