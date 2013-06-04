/*
 ** 2012 Januar 8
 **
 ** The author disclaims copyright to this source code.  In place of
 ** a legal notice, here is a blessing:
 **    May you do good and not evil.
 **    May you find forgiveness for yourself and forgive others.
 **    May you share freely, never taking more than you give.
 */
package scythemod.dragon;

import net.minecraft.util.MathHelper;

/**
 * Math helper class.
 * 
 * @author Nico Bergemann <barracuda415 at yahoo.de>
 */
public class MathX {

    public static final float PI_F = (float) Math.PI;
    public static boolean useLUT = true;
    
    /**
     * You no take constructor!
     */
    private MathX() {
    }

    // float sine function, may use LUT
    public static float sin(float a) {
        if (useLUT) {
            return MathHelper.sin(a);
        } else {
            return (float) Math.sin(a);
        }
    }

    // float cosine function, may use LUT
    public static float cos(float a) {
        if (useLUT) {
            return MathHelper.cos(a);
        } else {
            return (float) Math.cos(a);
        }
    }

    // float tangent function
    public static float tan(float a) {
        return (float) Math.tan(a);
    }

    // float atan2 function
    public static float atan2(float y, float x) {
        return (float) Math.atan2(y, x);
    }

    // float degrees to radians conversion
    public static float toRadians(float angdeg) {
        return (float) Math.toRadians(angdeg);
    }

    // float radians to degrees conversion
    public static float toDegrees(float angrad) {
        return (float) Math.toDegrees(angrad);
    }
    
    // normalizes a float angle to between +180 and -180
    public static float normalizeAngle(float a) {
        while (a > 180) {
            a -= 360;
        }
        while (a < -180) {
            a += 360;
        }
        return a;
    }
    
    // normalizes a double angle to between +180 and -180
    public static double normalizeAngle(double a) {
        while (a > 180) {
            a -= 360;
        }
        while (a < -180) {
            a += 360;
        }
        return a;
    }
    
    // float square root
    public static float sqrtf(float f) {
        return (float) Math.sqrt(f);
    }
    
    // numeric float clamp
    public static float clamp(float value, float min, float max) {
        return (value < min ? min : (value > max ? max : value));
    }
    
    // numeric double clamp
    public static double clamp(double value, double min, double max) {
        return (value < min ? min : (value > max ? max : value));
    }
    
    // numeric integer clamp
    public static int clamp(int value, int min, int max) {
        return (value < min ? min : (value > max ? max : value));
    }
    
    // float linear interpolation
    public static float lerp(float a, float b, float x) {
        return a * (1 - x) + b * x;
    }
    
    // double linear interpolation
    public static double lerp(double a, double b, double x) {
        return a * (1 - x) + b * x;
    }
    
    // smoothed float linear interpolation, similar to terp() but faster
    public static float slerp(float a, float b, float x) {
        if (x <= 0) {
            return a;
        }
        if (x >= 1) {
            return b;
        }
        
        return lerp(a, b, x * x * (3 - 2 * x));
    }
    
    // smoothed double linear interpolation, similar to terp() but faster
    public static double slerp(double a, double b, double x) {
        if (x <= 0) {
            return a;
        }
        if (x >= 1) {
            return b;
        }
        
        return lerp(a, b, x * x * (3 - 2 * x));
    }
    
    // float trigonometric interpolation
    public static float terp(float a, float b, float x) {
        if (x <= 0) {
            return a;
        }
        if (x >= 1) {
            return b;
        }

        float mu2 = (1 - cos(x * PI_F)) / 2.0f;
        return a * (1 - mu2) + b * mu2;
    }
    
    // double trigonometric interpolation
    public static double terp(double a, double b, double x) {
        if (x <= 0) {
            return a;
        }
        if (x >= 1) {
            return b;
        }

        double mu2 = (1 - Math.cos(x * Math.PI)) / 2.0;
        return a * (1 - mu2) + b * mu2;
    }
}
