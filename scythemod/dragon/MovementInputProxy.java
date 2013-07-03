/*
 ** 2012 August 28
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon;

/**
 * Helper class with identical structure to MovementInput. Required because the
 * original class doesn't exist on server side.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class MovementInputProxy {
    public float moveStrafe = 0;
    public float moveForward = 0;
    public boolean jump = false;
    public boolean sneak = false;
}
