# KJTagLayout
#### 绘制流程：
##### 从整体看：
1. 测量流程：从根 View 递归调⽤每⼀级⼦ View 的 measure() ⽅法，对它们进⾏测量
2. 布局流程：从根 View 递归调⽤每⼀级⼦ View 的 layout() ⽅法，把测量过程得出的⼦ View
的位置和尺⼨传给⼦ View，⼦ View 保存
##### 从个体看，对于每个 View：
1. 运⾏前，开发者在 xml ⽂件⾥写⼊对 View 的布局要求 layout_xxx
2. ⽗ View 在⾃⼰的 onMeasure() 中，根据开发者在 xml 中写的对⼦ View 的要求，和⾃⼰
的可⽤空间，得出对⼦ View 的具体尺⼨要求
3. ⼦ View 在⾃⼰的 onMeasure() 中，根据⾃⼰的特性算出⾃⼰的期望尺⼨
如果是 ViewGroup，还会在这⾥调⽤每个⼦ View 的 measure() 进⾏测量
4. ⽗ View 在⼦ View 计算出期望尺⼨后，得出⼦ View 的实际尺⼨和位置
5. ⼦ View 在⾃⼰的 layout() ⽅法中，将⽗ View 传进来的⾃⼰的实际尺⼨和位置保存
如果是 ViewGroup，还会在 onLayout() ⾥调⽤每个字 View 的 layout() 把它们的尺⼨
位置传给它们

#### tip
1. resolveSize：根据父view对自己的要求对自己测量的尺寸做矫正
2. measureChildWithMargins：根据开发者的要求（xml中的layout）以及自己的可用空间生成自己对子view的要求，并调用子view的measure方法测量子view
3. setMeasuredDimension: 将自己的尺寸保存起来，供父view布局的时候使用。
