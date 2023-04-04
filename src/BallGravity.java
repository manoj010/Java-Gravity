import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BallGravity extends JPanel implements ActionListener {
    private int width = 400;
    private int height = 500;
    private int ballSize = 20;
    private Ball ball;

     BallGravity() {
        // create a ball with initial position (x = width/2, y = ballSize), initial speed (0, 0), and gravity acceleration of 0.1
        ball = new Ball(width / 2, ballSize, 0, 0, 0.1);

        // set up the timer to update the ball's position every 10 milliseconds
        Timer timer = new Timer(10, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // update the ball's position
        ball.update();

        // check if the ball hits the bottom of the frame
        if (ball.getY() + ballSize > height) {
            // if the ball hits the bottom, reset its position and speed
            ball.setY(height - ballSize);
            ball.setSpeedY(-ball.getSpeedY() * 0.8); // apply some bounce by reversing the y speed and reducing it by 20%
        }

        // repaint the panel to show the updated ball position
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // draw the ball on the panel
        g.setColor(Color.BLUE);
        g.fillOval((int) ball.getX(), (int) ball.getY(), ballSize, ballSize);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Ball Gravity");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 500);
        frame.setContentPane(new BallGravity());
        frame.setVisible(true);
    }

    public class Ball {
        private double x; // x position of the ball
        private double y; // y position of the ball
        private double speedX; // horizontal speed of the ball
        private double speedY; // vertical speed of the ball
        private double acceleration; // acceleration due to gravity

        public Ball(double x, double y, double speedX, double speedY, double acceleration) {
            this.x = x;
            this.y = y;
            this.speedX = speedX;
            this.speedY = speedY;
            this.acceleration = acceleration;
        }

        public void update() {
            speedY += acceleration; // apply acceleration due to gravity
            x += speedX; // update x position based on horizontal speed
            y += speedY; // update y position based on vertical speed
        }

        // getters and setters
        public double getX() { return x; }
        public double getY() { return y; }
        public void setY(double y) { this.y = y; }
        public double getSpeedY() { return speedY; }
        public void setSpeedY(double speedY) { this.speedY = speedY; }
    }
}
