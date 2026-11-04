THE PARALLEL — Forge 26.2 / Forge 65.1.6 — source 0.5.0
Fan-made experimental analog-horror creature mod prototype.

CONTENTS
- Stalker, Tormentor and Virus hostile mob prototypes.
- Separate 64x64 entity textures.
- Humanoid renderer and basic pursuit/melee AI.
- GitHub Actions workflow configured to build with Java 25.

IMPORTANT: THIS IS SOURCE CODE, NOT A READY-TO-INSTALL MOD JAR.
The source has not yet passed a real Gradle build or an in-game test. Do not
rename this ZIP to .jar and do not put this ZIP in the mods folder.

TEST COMMANDS (after a successful build, in a world with cheats enabled)
/summon theparallel:stalker
/summon theparallel:tormentor
/summon theparallel:virus

BUILD ON GITHUB (can be done from a phone browser)
1. Download and extract this ZIP.
2. On github.com, create a new empty repository.
3. Upload the CONTENTS of the extracted folder to the repository root.
   IMPORTANT: include the hidden .github folder and the gradlew file.
4. Open the repository's Actions tab and enable workflows if prompted.
5. Choose “Build THE PARALLEL” and tap “Run workflow”.
6. Open the run. If it succeeds, download artifact “theparallel-forge-26.2”.
7. Extract that artifact ZIP. Put the .jar inside it into the Minecraft mods folder.

IF THE BUILD FAILS
Open the failed Actions run, expand the “Build mod” step, and send the full
error text or a screenshot here. That log is what is needed to fix any API or
Gradle incompatibility.

LOCAL BUILD REQUIREMENTS
Java 25 and internet access. Run ./gradlew build (Windows: gradlew.bat build).
Expected output: build/libs/theparallel-0.5.0.jar
