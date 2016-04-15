=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project Proposal
PennKey: 48511232 / amenarde
Name of TA you consulted with: __________
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===============
=: Your Game :=
===============

- What game are you planning to implement? If it is a game of your own design,
  or not especially well-known, provide a 2-3 sentence description of the game.
  
  Of my own design -- I want to make a 2-D space strategy and trading game with
  an emphasis on orbital dynamics i.e. the user must set trajectories smartly
  in order to move in some reasonable way between planets and space stations for
  trading.


- What classes and interfaces do you plan to create? How will the different
  components of your game (the model, the GUI, etc.) interact?
  
  Overall there will be a SpaceObject class that handles everything displayed
  on-screen. Then there will be a MovingObject and StationaryObject interface.
  Extending SpaceObject and implementing StationaryObject will be Celestials
  and Stations, the former of which will have Planets, BlackHoles, and 
  Supernovae as sub-classes. The Ship will currently be the only implementation
  of MovingObject.
  
  Things will interact with the ship due to their gravitational effect on the
  ship and through collisions, which will either kill the ship (planets) or
  initialize trades (space stations). The ship will need to be constantly redrawn
  and there will be constant mechanics calculations and collision checking.
  
  Challenge Problem (Kudos Only): Implement a prediction line that will help
  the user see their currently trajectory and how it is actively changing.


- What do you think will be the most challenging thing to implement?

  I expect the orbital dynamics to end up being extremely complicated,
  and more so than even implementing them, balancing them so that the game
  is not impossible or too easy or too slow.


====================
=: Core Concept 1 :=
====================

Concept 1: Advanced Collisions

- What specific feature of your game will be implemented using this concept?

  Spaceship to celestial body collisions as well as spaceship to space station
  collisions will drive one of the main premises of the game, so there will need
  to be complex shape collision boxes to determine those outcome.

- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.

  This is a natural fit in the implementation here, since there is a direct 
  physical correlation from the concept to the real world I will be trying to 
  simulate.
  
====================
=: Core Concept 2 :=
====================

Concept 2: Inheritance

- What specific feature of your game will be implemented using this concept?

  As I mentioned higher up, a lot of this game will be based on inheritance.
  There will be multiple difference objects the user can interact with that 
  will all remain on screen (some with positive gravity, negative gravity,
  no gravity, the user can interact with, can't interact with). As well as
  the user's space ship, which will include inheritance for the possibility
  of later adding other movable celestial bodies or space stations.

- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.

  Since there will be a large number of things on screen (planets, stations,
  the user), it would be silly to have to recreate the wheel for everything, 
  especially when collisions would mostly behave the same for everything, GUI
  placement would be the same, orbital dynamics would be the same, etc.

====================
=: Core Concept 3 :=
====================

Concept 3: Physics-Based Animations

- What specific feature of your game will be implemented using this concept?

  Hella space stuff. There's gonna be some of Kepler's laws in there, Laplace-
  Runge-Lenz vectors, elliptical orbits, escape velocities, parabolic orbits,
  hyperbolic orbits, conic orbits, there will be an empire, a rebellion, Han Solo,
  Scotty. 
  
  But seriously, hella orbits.

- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.

  The idea here is that it is this feature that makes this game novel. You could
  scratch the orbital dynamics and have a game where the user has complete control,
  but I like the realistic (unrealism) of this idea. Essentially this feature is what
  makes this not another-90s-2D-space-rts.

====================
=: Core Concept 4 :=
====================

Concept 4: Collections

- What specific feature of your game will be implemented using this concept?

  Collections will be used to efficiently calculate all the orbital dynamics
  effecting the ship from the planets (the collections will store the stationary
  objects) as well as check for collisions. I am suspecting this will be the
  more demanding part of the program so using TreeSets and stuff to quickly index
  through planets may prove to be useful. 
  
  Also, depending on how far I get along on trading, the resources of the space-
  stations, the buy-sell mechanisms, and the users goods and money will have to be
  stored in collections that allow for effective resource trading.

- Why does it make sense to implement this feature with this concept? Justify
  why this is a non-trivial application of the concept in question.

  I'm currently worried that the orbital dynamics and the number of relationships
  in the simulator will make the game resource-intensive (at least on small machines),
  so using effective collections may make the process speedier or even make it possible to
  not have to run every check every iteration through.
