// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Button;

public class DPad extends Button{
    Joystick m_joystick;
    Direction m_direction;

public DPad(Joystick driverController, Direction direction) {
    m_joystick = driverController;
    m_direction = direction;
    }

    public static enum Direction {
        UP(0), RIGHT(90), DOWN(180), LEFT(270);
        int direction;

        private Direction(int direction){
            this.direction = direction;
         }
    }

    public boolean get() {
        int dpadValue = m_joystick.getPOV();
        return (dpadValue == m_direction.direction) || (dpadValue == (m_direction.direction + 45) % 360)
        || (dpadValue == (m_direction.direction + 315) % 360);

    }

}
