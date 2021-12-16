import javax.swing.JFrame;

public class DataFrameViewer
{  
  public static void main(String[] args)
  {  
    JFrame frame = new JFrame();
    DataCounterPanel data = new DataCounterPanel();
    frame.add(data);

    frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
  private static final int FRAME_WIDTH = 800;
  private static final int FRAME_HEIGHT = 200;
}
