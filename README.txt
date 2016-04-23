“Exhaust” is a real-time space strategy game that requires a good eye for gravitational fields to keep your ship alive! Features include multiple levels and captivating low-res graphics.

__________________________________________________________________

Made by Antonio Menarde for his CIS 120 final homework.

Game.java contains the top-level frame, handles creating the task bar resources, an instance of the game, and handles screen-switching between the game and intro/exit screens.

GameCourt.java contains the game, which includes building the screen, handling moving, checking for collisions, and changing between levels (as well as containing the level information.)

SpaceObject.java contains position information for any object on the game screen, and also includes an intersect method for checking for collisions between objects.

SpaceShip.java adds movement functionality to SpaceObject through vector velocities, allowing for the ship to actively move and check that it is within the game boundary. 

Celestial.java allows certain SpaceObjects to have gravity and be able to affect other SpaceObjects.
__________________________________________________________________

CIS 120 Core Concepts:

Advanced Collisions: Allows images to intersect for pixels where both of their alphas are not non-zero.

Advanced Physics: Game implements stable orbital physics and gravity between multiple bodies, namely between planets and the user’s spaceship.

Inheritance: SpaceObject for general objects on screen. The Celestial subclass allows for SpaceObjects that affect other things, and the SpaceShip subclass, which implements MoveableObject, allows for things that can be affected.

Collections: Collections are used to effectively and efficiently check for intersections between large numbers of objects and similarly to allow the gravity of a large number of objects to affect an object. They are also used to make the collision system more efficient than directly doing operations on images.

__________________________________________________________________

Bibliography: 
 CIS 120 Game HW
 University of Pennsylvania
 @version 2.0, Mar 2013

 Stack Overflow Question Numbers:
 (Used only for Swing nuances, not “Core Concepts”)
 2420742
 10157954
 5652344
 18027833
 3342651
 18871150
 1097366

 Gameplay and some visual resources inspired by:
 Faster than Light
 Cowboy Bebop

 Font User: Orator Std