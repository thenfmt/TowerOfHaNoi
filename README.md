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
       - Chương trình sử dụng class Texture để load ảnh từ file và quản lí chúng 
          
            ```
	    	public class Texture {
			public BufferedImage rodImg[] = new BufferedImage[2];
			public BufferedImage diskImg[] = new BufferedImage[3];
			public BufferedImage iconNormal[] = new BufferedImage[10];
			public BufferedImage iconHover[] = new BufferedImage[10];
			public BufferedImage background;

			LoadImage loader = new LoadImage();
			/*
			 * load hình ảnh thông qua class LoadImage và lưu vào những mảng riêng biệt
			 * normal là hình hình ảnh ở trạng thái bình thường. Khi ta di chuyển chuột hoặc click vào object thì sẽ chuyển sang hover icon hoặc clicked icon
			 */
			public Texture() {
				background = loader.loadImage("/background/background.png");

				getObjects();
				getIcons();
			}

			private void getObjects() {
				//disks
				diskImg[0] = loader.loadImage("/disks/disk_normal.png");
				diskImg[1] = loader.loadImage("/disks/disk_hover.png");
				diskImg[2] = loader.loadImage("/disks/disk_clicked.png");

				//rods
				rodImg[0] = loader.loadImage("/rods/rod_normal.png");
				rodImg[1] = loader.loadImage("/rods/rod_hover.png");
			}

			private void getIcons() {
				for(int i = 0; i < 9; i++) {
					//normal icon
					iconNormal[i] = loader.loadImage("/icons/normal/icon (" + (i+1) + ").png");

					//hover icon
					iconHover[i] = loader.loadImage("/icons/hover/icon (" + (i+1) + ").png");
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
   4. Custom JButton and JLabel
      - CircleButton:
        - Những button được chuyển từ mặc định (hình chữ nhật) sang hình tròn
          ```
		  public class CircleButton extends JButton implements MyShape{
			ImageIcon normalIcon;
			ImageIcon hoverIcon;

			public CircleButton(int radius, int iconIndex) {
				super();
				setBackground(new Color(106, 139, 158));
				setFocusable(false);
				normalIcon = new ImageIcon(Frame.texture.iconImg[iconIndex].getScaledInstance(radius, radius, Image.SCALE_DEFAULT));
				hoverIcon = new ImageIcon(Frame.texture.iconImg[iconIndex+8].getScaledInstance(radius, radius, Image.SCALE_DEFAULT));
				setIcon(normalIcon);

				// enlarge the button so that is becomes a circle rather than an oval
				Dimension size = getPreferredSize();
				size.width = size.height = Math.max(size.width, size.height);

				//this call cause the JButton not to paint the background. Allows us to paint the background
				setContentAreaFilled(false);
				setRolloverEnabled(false);
				setFocusPainted(false);

				addMouseListener(new MouseAdapter() {
				 public void mouseEntered(MouseEvent e) {
					 setIcon(hoverIcon);
				 }
				 public void mouseExited(MouseEvent e) {
					 setIcon(normalIcon);
				 }
			      });
			}


			protected void paintComponent(Graphics g) {
			    if (getModel().isArmed()) {
			      g.setColor(Color.lightGray);
			    } else {
			      g.setColor(getBackground());
			    }
			    g.fillOval(0, 0, getSize().width, getSize().height);

			    super.paintComponent(g);
			}


			 Shape shape;
			 public boolean contains(int x, int y) {
				 if (shape == null || !shape.getBounds().equals(getBounds())) {
					 shape = setShape(getWidth(), getHeight());
			    }
			    return shape.contains(x, y);
			 }


			public Shape setShape(int width, int height) {
				Shape s = new Ellipse2D.Float(0, 0, width, height);
				return s;
			}
		  }
		  
      - CustomJLabel: load và cài đặt font chữ, font size chung cho các JLabel
         ```
		public class CustomLabel extends JLabel{
			public CustomLabel(String title, int fontSize) {
				super(title);
				Font font = new Font("Arial", Font.BOLD, fontSize);

				try {
					font = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/SP3-Traveling-Typewriter.otf").openStream());
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   
				
				GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
				genv.registerFont(font);
				font = font.deriveFont(Font.BOLD, (float)fontSize);


				setFont(font);
				setForeground(new Color(206, 202, 191));
			}

		}
		
            
   5. Xử lí logic trong game
      - Các Panel:
        - Game: xử lí các hoạt động của game, những đặc tính chung của gamePlay, hint
        - GamePlay: được kế thừa từ panel Game, được phép di chuyển các đĩa qua lại 
        - Hint: được kế thừa từ panel Game, không được phép di chuyển các đĩa qua lại, nhưng có thể xem những gợi ý của chương trình từ đây.
      - Panel Game: 
        - Tại constructor của Game sẽ khởi tạo những button và những label cho giao diện. Constructor cũng gọi phương thức khởi tạo những objects (rod, disk)
        - Số lượng disk được khởi tạo nằm trong khoảng [3;5] được người dùng lựa chọn từ giao diện Launcher thông qua biến "height" kiểu "public static int". Vì là biến static nên biến height được lưu tại heap memory và có thể chỉnh sửa từ class khác.
        	```
			public Game() {
				setLayout(null);
				setBounds(0, 0, 1080, 720);
				
				//components
				CircleButton btnLauncher = new CircleButton(35, 5);
				btnLauncher.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Frame.launcherOn();
					}
				});
				btnLauncher.setBounds(912, 11, 50, 50);
				add(btnLauncher);
				
				lblStepCounter = new CustomLabel("Step: 0", 20);
				lblStepCounter.setBounds(339, 31, 133, 20);
				add(lblStepCounter);
				
				CustomLabel lblStepExpect = new CustomLabel("Expect Step: " + (int)(Math.pow(2, height)-1), 20);
				lblStepExpect.setBounds(535, 25, 214, 26);
				add(lblStepExpect);
				
				lblNoti = new CustomLabel("Not legal!", 20);
				lblNoti.setBounds(926, 103, 133, 35);
				lblNoti.setVisible(false);
				add(lblNoti);
				
				background = new JLabel(new ImageIcon(Frame.texture.background));
				background.setBounds(getBounds());
				initObject(height);
			}

        - Tại method initObject(height) gọi phương thức initRods() trước khi khởi tạo các disks
        	```
			public void initObject(int height) {
				// khởi tạo rod nhưng chưa thêm vào panel
				initRods();

				// khởi tạo các đĩa rồi thêm vào panel
				// ban đầu tạo mội đĩa base ảo (có shape và position nhưng không thể nhìn thấy và tương tác) để làm đĩa gốc
				// các đĩa sau base sẽ giảm đi chiều width-38 và height-5 để tạo sự cân đối cho ảnh.
				// lí do chọn số 38 và 5: qua các phép thử thấy cân đối nên giữ nguyên
				Disk baseDisk = rod[1].peekStack();
				for(int i = 0; i < height; i++) {
					ImageIcon diskIconNormal = new ImageIcon(Frame.texture.diskImg[0].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT));
					ImageIcon diskIconHover = new ImageIcon(Frame.texture.diskImg[1].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT));
					ImageIcon diskIconClicked = new ImageIcon(Frame.texture.diskImg[2].getScaledInstance(baseDisk.getWidth()-38, baseDisk.getHeight()-5, Image.SCALE_DEFAULT));

					disk = new Disk(diskIconNormal, diskIconHover, diskIconClicked);

					disk.setLocation(baseDisk.getX()+19, baseDisk.getY()-baseDisk.getHeight()+10);
					disk.setShape(baseDisk.getWidth()-38, baseDisk.getHeight()-5);
					add(disk);
					rod[1].pushStack(disk);

					baseDisk = disk;
				}

				// thêm các rod vào panel: nếu thêm trước lúc khởi tạo đĩa thì sẽ đè lên đĩa làm mất thẩm mỹ
				add(rod[1]);
				add(rod[2]);
				add(rod[3]);
			}

        - Tại method initRods() khởi tạo các baseDisk không thể tương tác push vào stack chứa các disk.
        - Thêm những mouseEvent khi entered, exited hoặc clicked sẽ có những effect 
        - Khi click chuột: nếu chưa có rod nào đó được pick thì sẽ setPick(true), nếu đã có rod được pick trước đó thì sẽ thực hiện di chuyển đĩa ở top của rod đã pick trước đó sang top của rod vừa mới được click qua hàm moveDisk(idFrom, idTo).
        	```
			rod[1] = new Rod(new ImageIcon(Frame.texture.rodImg[0].getScaledInstance(32, 300, Image.SCALE_DEFAULT)),
						 new ImageIcon(Frame.texture.rodImg[1].getScaledInstance(32, 300, Image.SCALE_DEFAULT)));

			rod[1].setShape(32, 300);
			rod[1].setLocation(270, 200);
			disk = new Disk(null, null, null);
			disk.setEnabled(false);
			disk.setBasekDisk(true);
			disk.setBounds(151, 500, 270, 63);
			rod[1].pushStack(disk);
			rod[1].addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			    if(e.getClickCount() > 0 && isAllowPick()){
				if(isRodPick()==false) {
					setRodPick(1); // pick rod with id = 1
				} else if(rod[1].isPick()==true){
					unPickRod(1); // unpick rod with id = 1
				} else {
					moveDisk(getRodPick(), 1);
				}
			    }
			}
			public void mouseEntered(MouseEvent e) {
				 rod[1].setIconHover();
				 if(!rod[1].isPick())
					 rod[1].peekStack().setIconHover();
			}
			public void mouseExited(MouseEvent e) {
				 rod[1].setIconNormal();
				 if(!rod[1].isPick())
					 rod[1].peekStack().setIconNormal();
			}
		    });



        - Khi gọi phương thức moveDisk(from, to), sẽ kiểm tra xem di chuyển có hợp lệ hay không, thông qua phương thức isMoveLegal(from, to).
        - Nếu di chuyển hợp lệ sẽ thực hiện pop disk ở top stack của rod "from" và push vào stack của rod "to"
        - Sau khi thực hiện các bước di chuyển sẽ kiểm tra game đã kết thúc hay chưa thông qua phương thức isWin()
        - Phương thức isWin() kiểm tra bằng cách kiểm tra xem những disk có thể di chuyển ở các rod1 và rod2. nếu không còn đĩa nào có thể di chuyển có nghĩa tất các các disk đã ở vị trí cột thứ 3.
        	```	
			public boolean isMoveLegal(int from, int to) {
				Disk upper = rod[from].peekStack();
				Disk below = rod[to].peekStack();
				if(upper.getWidth()<below.getWidth() && upper.getHeight()<below.getHeight())
					return true;
				else
					notiNotLegal();

				return false;
			}
			
			public boolean isWin() {
				if(rod[1].peekStack().isBasekDisk() && rod[2].peekStack().isBasekDisk())
					return true;

				return false;
			}

			public void moveDisk(int from, int to) {
				if(isMoveLegal(from, to) && isAllowMove()) {
					step++;
					lblStepCounter.setText("Step: " + step);
					rod[from].setPick(false);
					Disk disk = rod[from].popStack();
					Disk baseDisk = rod[to].stack.peek();
					disk.setBounds(baseDisk.getX()+(baseDisk.getWidth()-disk.getWidth())/2, baseDisk.getY()-disk.getHeight()+5, disk.getWidth(), disk.getHeight());
					disk.setIcon(new ImageIcon(Frame.texture.diskImg[0].getScaledInstance(disk.getWidth(), disk.getHeight(), Image.SCALE_DEFAULT)));
					rod[to].pushStack(disk);

					if(isWin())
					Frame.winOn();
				}
			}


			public boolean isAllowMove() {
				return allowMove;
			}


      - Panel GamePlay:
      	- Game play được kế thừa từ panel Game
	- GamePlay được phép chọn và di chuyển các đĩa qua lại.
			```
			public class GamePlay extends Game {

				public GamePlay() {
					super();
					setAllowMove(true);
					setAllowPick(true);

					CircleButton btnHint = new CircleButton(30, 4);
					btnHint.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Frame.HintOn();
						}
					});
					btnHint.setBounds(110, 25, 30, 30);
					add(btnHint);

					add(background);
				}
			}

      - Panel Hint: 
        - Không giống như GamePlay, hint không thể chọn các object hay di chuyển các disk qua lại
      	- Hint được kế thừa từ panel Game
      	- List step lưu những bước di chuyển để qua màn
			```
			private List<int[]> step = new ArrayList<int[]>();
			public void solution() {
				recusive(GamePlay.height, 1, 3, 2);
			}

			public void recusive(int height, int A, int C, int B) {
				if(height==0)
					return;
				recusive(height-1, A, B, C);
				int arr[] = {A, C};
				step.add(arr);
				recusive(height-1, B, C, A);
			}
			
        - Không giống như 
        - Thông qua những phương thức moveNext(), moveBack() để xem từng bước di chuyển mà chương trình đề xuất.
        - Chương trình có hỗ trợ phương thức autoRun() theo trình tự từng bước với delay=1s cho mỗi bước di chuyển
			```
			public void moveNext() {
				index = Math.min(step.size(), index);
				if(index >= step.size()) {
					notiNotLegal();
					return;
				}

				int from = step.get(index)[0];
				int to = step.get(index)[1];
				moveDisk(from, to);
				index++;
			}

			public void moveBack() {
				index--;
				index = Math.max(0, index);

				int from = step.get(index)[1];
				int to = step.get(index)[0];
				moveDisk(from, to);
			}


			public void autoRun() {
				timer = new Timer(1000, new ActionListener() {
					  @Override
					  public void actionPerformed(ActionEvent arg0) {
						if(isPause()==false)
							moveNext();
						else 
							timer.stop();
					  }
					});
				timer.setRepeats(true); 
				timer.start(); 
			}
