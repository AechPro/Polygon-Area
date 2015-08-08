# Polygon-Area
A simple program I made for a personal challenge to find the area inside any 2D polygon. This is done by finding the highest 3 points of the polygon, calculating the area inside those three points, then removing the highest point of the polygon and repeating.

NOTE: this calculation includes the area of any polygons inside the original polygon. I.E. if this program is run with a square that has a triangle on the inside of it as the input parameters, both the area of the square and the area of the triangle will be included in the calculation. I consider this a bug, and hope to fix it in future.
