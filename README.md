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
