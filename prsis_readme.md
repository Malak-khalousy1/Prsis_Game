# PRSIS: A Simulation of Barjees Game Using Intelligent Search Algorithms

## Damascus University – Faculty of Informatics Engineering

### Artificial Intelligence Department – Practical Project: Intelligent Search Algorithms

---

## English Version

### 🎓 Project Title:

**PRSIS: A Simulation of Barjees Game Using Intelligent Search Algorithms**

###  Project Objective

This project presents a command-line simulation of the traditional board game **Barjees** (also known as *PRSIS*), implemented in **Java**. The project explores the application of **intelligent search algorithms** in decision-making within adversarial, non-deterministic environments. It was developed as part of the coursework for the “Intelligent Search Algorithms” subject.

### 🎮 Gameplay Modes

The game offers four modes of play via a simple command-line interface:

```
Choose Game Type:
q: Quit
1 - User vs. User
2 - User vs. Computer (Minimax Algorithm)
3 - User vs. Computer (Alpha-Beta Pruning)
4 - User vs. Computer (Expectiminimax Algorithm)
```

Each mode allows players to experience different levels of AI strategy and complexity.

### 🔬 Key Features

- **Shell-based Randomness**: Simulates traditional Barjees shell throw (e.g., Dost, Beng, Shikka).
- **Game Tree Search Algorithms**:
  - `Minimax`: Classical two-player adversarial planning.
  - `Alpha-Beta`: Enhanced pruning to reduce computation.
  - `Expectiminimax`: Includes chance nodes for realistic probability-based decisions.
- **Heuristic Evaluation**: Custom function evaluates board state considering both player positions and tactical threats.
- **Trace Output Option**: Optional debug output includes:
  - Number of nodes visited
  - Evaluation values
  - Node types (MAX / MIN / CHANCE)
  - Selected move and reasoning

### 📂 Project Structure

```
src/
├── Board/       // Board structure and layout
├── Logic/       // Game logic and algorithm integration
├── Node/        // Search tree node models
├── Position/    // Movement and board positioning
├── Throw/       // Shell throw simulator
├── Main.java    // Entry point and game mode selector
```

### ⚙️ Technologies Used

- **Language**: Java
- **IDE**: IntelliJ IDEA
- **Paradigm**: Object-Oriented Programming (OOP)
- **Algorithms**: Minimax, Alpha-Beta Pruning, Expectiminimax


### 💻 How to Run

```bash
javac src/Main.java
java src.Main
```

---



###  عنوان المشروع:

**محاكاة لعبة البرجيس باستخدام خوارزميات البحث الذكي**

###  هدف المشروع

يعرض هذا المشروع محاكاة برمجية للعبة "البرجيس" التقليدية من خلال واجهة سطر أوامر وتم تطويره باستخدام **Java**. يستكشف المشروع تطبيق خوارزميات البحث الذكي في مواجهات غير حتمية بين لاعبين.

### 🎮 أنماط اللعب

يوفر البرنامج 4 خيارات لنمط اللعب عن طريق واجهة CLI:

```
Choose Game Type:
q: Quit
1 - لاعب ضد لاعب
2 - لاعب ضد الحاسوب (Minimax)
3 - لاعب ضد الحاسوب (Alpha-Beta)
4 - لاعب ضد الحاسوب (Expectiminimax)
```

###  الميزات الرئيسية

- رمي ودع غير حتمي وصادق للواقع.
- تطبيق لخوارزميات:
  - `Minimax`
  - `Alpha-Beta`
  - `Expectiminimax`
- تابع تقييم heuristic يقيم الوضع لكل لاعب.
- إمكانية عرض trace لعملية البحث وعقد التقييم.

### 📂 بنية المشروع

```
src/
├── Board       منطق الرقعة
├── Logic       المحرك والذكاء الصنعي
├── Node        عقد شجرة البحث
├── Position    منطق الحركة
├── Throw       مولد رمي الودع
├── Main.java   نقطة تشغيل اللعبة
```

### ⚙️ التقنيات المستخدمة

- **لغة البرمجة**: Java
- **بيئة التطوير**: IntelliJ IDEA
- **المنهجية**: OOP
- **الخوارزميات**: Minimax و Alpha-Beta و Expectiminimax



### 💻 كيفية التشغيل

```bash
javac src/Main.java
java src.Main
```


