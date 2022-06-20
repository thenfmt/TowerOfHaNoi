# TowerOfHaNoi

## Giới thiệu
  - Bài toán "Tower of Ha Noi" là một trong những bài toán kinh điển mà những người mới học lập trình sử dụng để tiếp cận với đệ quy trong cấu trúc dữ liệu và giải thuật.
  - Project này sử dụng Java Swing để trực quan hóa bài toán "Tower of Ha Noi".
## Cách cài đặt
  1. Yêu cầu 
      - Java Development Kit (JDK)
      - IDE (recommended Eclipse)
  2. Clone project về mà mở trong workspace của IDE

## Cách sử dụng
  Có 3 cửa sổ chính: launcher, gamePlay và hint
  1. Launcher
      - <img src="/images/launcher.png" width="500">.
      - Tại launcher bạn có thể click vào icon <img src="/images/play.png" width="20"> để bắt đầu màn hình gamePlay
      - Hoặc thoát chương trình bằng cách click vào icon <img src="/images/exit.png" width="20">
      - Bạn cũng có thể tăng hoặc giảm số lượng đĩa bằng các icon <img src="/images/minus.png" width="20"> và <img src="/images/add.png" width="20">
      
  2. Game play
      - <img src="/images/game_play.png" width="500">.
      - Nhiệm vụ của bạn là di chuyển các đĩa từ cột đầu tiên sang đến cột cuối cùng sao cho trong toàn bộ quá trình đĩa phía dưới luôn luôn lớn hơn đĩa ở phía trên.
      - Bạn có thể sử dụng cột ở giữa làm cột trung gian. 
      - Khi số đĩa đã nằm đúng vị trí thì bạn đã hoàn thành thử thách
      - Nếu không thể tìm được lời giải hoặc không thể giải với số bước ngắn nhất thì bạn có thể tham khảo lời giải của chúng tôi bằng cách click vào icon <img src="/images/help.png" width="20">
      - Bạn cũng có thể trở về màn hình Launcher thông qua biểu tượng <img src="/images/home.png" width="20"> để thay đổi số lượng đĩa.
     
   3. Hint 
      - <img src="/images/hint.png" width="500">.
      - Bạn có thể xem các bước mà chúng tôi gợi ý thông qua các icon <img src="/images/back.png" width="20"> và <img src="/images/next.png" width="20">
      - Bạn cũng có thể bắt đầu game mới hoặc trở về menu thông qua các biểu tượng <img src="/images/play.png" width="20"> và <img src="/images/home.png" width="20">

## Giải thích code
   1. Cấu trúc 
      - <img src="/images/layout.png" width="400">.
      - Khái niệm:
          - Frame là một container(Nơi chứa và sắp xếp các component khác của Java Swing, có thể là các container khác) trong ứng dụng Java Swing
          - Lớp CardLayout trong Java Swing quản lý các thành phần theo một phương thức mà chỉ có một thành phần là nhìn thấy (visible) tại một thời điểm.
          - Panel là một lớp container đơn giản nhất. Nó cung cấp không gian trong đó một ứng dụng có thể đính kèm bất kỳ thành phần nào khác. Nó kế thừa lớp Container.
      - Cấu trúc chương trình:
      - <img src="/images/sys_layout.png" width="400">.
      - Chương trình sử dụng một frame, một contentPane chính và chọn cardlayout làm layout management. trong đó cardlayout chứa các panels Launcher, GamePlay, Hint và Win.
      - Chương trình sử dụng các method launcherOn(), gameOn(), hintOn() và winOn() để chuyển qua lại giữa các panel.
      - Khi panel launcher được khởi tạo ngay tại constructor của frame và được hiển thị đầu tiên khi chạy chương trình.
      - Các phương thức gameOn(), hintOn() và winOn() tạo mới panel khi chuyển qua lại giữa các panels.
          ```
            public static void gameOn() {
                GamePlay gamePlay = new GamePlay();
                contentPane.add("Game", gamePlay);
                cardLayout.show(contentPane, "Game");
            }

            public static void launcherOn() {
                cardLayout.show(contentPane, "Launcher");
            }

            public static void winOn() {
                Win win = new Win();
                contentPane.add("Win", win);
                cardLayout.show(contentPane, "Win");
            }

            public static void HintOn() {
                Hint hint = new Hint();
                contentPane.add("Hint", hint);
                cardLayout.show(contentPane, "Hint");
            }
            
            
   2. Xử lý ảnh
       - Khái niệm:
          - JLabel: Lớp JLabel trong Java Swing có thể hiển thị hoặc text, hoặc hình ảnh hoặc cả hai. Các nội dung của Label được gán bởi thiết lập căn chỉnh ngang và dọc trong khu vực hiển thị của nó.
          - SpriteSheet: Có thể hiểu đơn giản SpriteSheet là một bức ảnh lớn chứa nhiều bức ảnh nhỏ. Chương trình sử dụng SpriteSheet bởi vì có thể tối ưu được thời gian load file từ chương trình. Thay vì tạo nhiều request load file, ta chỉ cần load 1 sheet image và cắt các subimage từ sheet image
       - Chương trình sử dụng class LoadImage để load ảnh từ file
          - LoadImage
            ```
              public class LoadImage {
                private BufferedImage image;

                public BufferedImage loadImage(String path) {
                  try {
                    image = ImageIO.read(getClass().getResource(path));
                  } catch (IOException e) {
                    e.printStackTrace();
                  }
                return image;
                }
              }
       - Chương trình sử dụng class Texture để cắt ảnh từ SpriteSheet và quản lí chúng
          - SpriteSheet:
            ```
              public class SpriteSheet {
                private BufferedImage image;

                public SpriteSheet(BufferedImage image) {
                  this.image = image;
                }

                public BufferedImage grabImage(int col, int row, int width, int height) {
                  BufferedImage img = image.getSubimage((col*width)-width, (row*height)-height, width, height);
                  return img;
                }
              }

          - Texture:
            ```
              public class Texture {

                  SpriteSheet iconSheet, objectSheet;

                  private BufferedImage iconImage = null;
                  private BufferedImage objectImage = null;

                  public BufferedImage rodImg[] = new BufferedImage[2];
                  public BufferedImage diskImg[] = new BufferedImage[3];
                  public BufferedImage iconImg[] = new BufferedImage[20];
                  public BufferedImage background;

                  public Texture() {
                    LoadImage loader = new LoadImage();
                    objectImage = loader.loadImage("/object.png");
                    background = loader.loadImage("/background.png");
                    iconImage = loader.loadImage("/icons.png");

                    iconSheet = new SpriteSheet(iconImage);
                    objectSheet = new SpriteSheet(objectImage);

                    getTextures();
                  }

                  private void getTextures() {
                    //game objects
                    rodImg[0] = objectSheet.grabImage(1, 1, 32, 32*8);
                    rodImg[1] = objectSheet.grabImage(2, 1, 32, 32*8);

                    diskImg[0] = objectSheet.grabImage(1, 6, 29*8, 29*2);
                    diskImg[1] = objectSheet.grabImage(2, 6, 29*8, 29*2);
                    diskImg[2] = objectSheet.grabImage(3, 6, 29*8, 29*2);

                    //icon
                    for(int i = 0; i < 16; i++) {
                      if(i >= 8)
                        iconImg[i] = iconSheet.grabImage(i+1-8, 2, 50, 50);
                      else
                        iconImg[i] = iconSheet.grabImage(i+1, 1, 50, 50);
                    }
                  }
              }
       - Chương trình sử dụng JLabel và coi ảnh là icon của JLabel 
          - Vì ảnh không phải là một component nên không thể thêm trực tiếp vào JPanel. Thông qua JLabel ta có thể thêm ảnh vào Jpanel dưới dạng icon của JLabel.
   3. Các đối tượng
       - Chương trình có hai đối tượng chính Rod và Disk. Cả hai đều được kế thừa từ JLabel để thêm ảnh dưới dạng icon như đã nói ở trên và implements interface MyShape.
         - Interface MyShape: Có duy nhất phương thức setShape() để định hình cho object
           ```
              public interface MyShape {
                Shape setShape(int width, int height);
              }
          - Disk
            ```
            public class Disk extends JLabel implements MyShape{
              public Disk(ImageIcon img) {
                super(img);
              }

              @Override
              public Shape setShape(int width, int height) {
                this.setSize(width, height);
                return null;
              }
            }
            
           - Rod: Sử dụng stack để lưu chứ và quản lí số Disk nằm tại các cột.
             ```
             public class Rod extends JLabel implements MyShape {
	
                private boolean pick = false;
                public Stack<Disk> stack = new Stack<Disk>();

                public Rod(ImageIcon img) {
                  super(img);
                }

                public void pushStack(Disk disk) {
                  stack.push(disk);
                }

                public Disk peekStack() {
                  return stack.peek();
                }

                public Disk popStack() {
                  return stack.pop();
                }

                public boolean isPick() {
                  return pick;
                }

                public void setPick(boolean pick) {
                  this.pick = pick;
                }

                @Override
                public Shape setShape(int width, int height) {
                  this.setSize(width, height);
                  return null;
                }

              }


        
