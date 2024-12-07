# Doodle
CISC 682 - IA08/IA09: Doodle App

## App Demo
Required Features Demo Video: https://youtu.be/OoudZc0LD0w
Custom Feature Demo Video: https://youtu.be/dA7iPhMHlsk

## How to Run the App
1. Open Android Studio.
2. Select `Get from VCS` in the top right.
3. Paste this repository's Git Cloning web URL in the `URL` box.
4. Click the `Clone` button in the bottom right.
5. After a few moments, a triangular play button should appear in the top bar. Click on it to build and run the app.
6. This should open up the app in your emulator. Make sure you are using the Pixel 9 Pro XL.
If your emulator does not appear:
   - In the Panel on the right side of the screen, click on Device Manager.
   - Select the Pixel 9 Pro XL as your device.
   - Click the play button again.
8. That's it! The app should now be running on the emulator, ready for your doodles!

------------------- OR -------------------

1. Git Clone or Download the code in this repository.
2. Open the code in Android Studio.
3. Click on the `Add Configuration` dropdown at the top and select `Edit Configurations...`.
4. Click on `Add new run configuration` and then select `Android App` from the options that pop up.
   - Name the configuration `app`.
   - Select `Doodle.app.main` as the Module.
   - Click `Apply` and then `OK`.
5. In the panel on the right side of the screen, click on `Device Manager`.
6. Select the Pixel 9 Pro XL as your device.
7. Change your file view to the `Project` view on the top left corner dropdown (you're likely on `Android` view initially).
8. Create a file called `local.properties` under Doodle/gradle/.
9. In this file, input `sdk.dir=YourSDKLocation`. This is what mine looks like for reference: `sdk.dir=/Users/aparnaroy/Library/Android/sdk`.
10. Build and run the project by clicking the hammer (or the circle arrow) button at the top.
11. That's it! The app should now be running on the emulator, ready for your doodles!

## Sources / References
- http://developer.android.com/training/custom-views/create-view.html
- http://developer.android.com/guide/topics/graphics/2d-graphics.html
- http://developer.android.com/training/custom-views/custom-drawing.html
- https://developer.android.com/reference/android/view/MotionEvent
- https://stackoverflow.com/questions/17674634/saving-and-reading-bitmaps-images-from-internal-memory-in-android

### Attributions
- Color Wheel Icon: <a href="https://www.flaticon.com/free-icons/art-and-design" title="art and design icons">Art and design icons created by Color creator - Flaticon</a>
- Pen Icon: <a href="https://www.flaticon.com/free-icons/text" title="text icons">Text icons created by Freepik - Flaticon</a>
- Eraser Icon: <a href="https://www.flaticon.com/free-icons/eraser" title="eraser icons">Eraser icons created by Those Icons - Flaticon</a>
- Menu Icon: <a href="https://www.flaticon.com/free-icons/dots" title="dots icons">Dots icons created by Ayub Irawan - Flaticon</a>
