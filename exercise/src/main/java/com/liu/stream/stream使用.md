### 一、筛选和切片

**筛选（filter，distinct）**

``` java
//谓词筛选filter:该操作会接受一个谓词（一个返回boolean的函数）作为参数，
//并返回一个包括所有符合谓词的元素的流
        List<Dish> collect = menu.stream()
            .filter(Dish::isVegetarian)
            .collect(Collectors.toList());
        System.out.println(collect);
//筛选各异的元素distinct：它会返回一个元素各异（根据流所生成元素的hashCode和equals方法实现）的流。
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .forEach(System.out::println);
```

**截短（limit）**

```java
 //截短limit：该方法会返回一个不超过给定长度的流。
        List<Dish> collect2 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(collect2);
```

**跳过**

```java
//跳过skip:返回一个扔掉了前n个元素的流。如果流中元素不足n个，则返回一个空流。
        //skip与limit是互补的
        List<Dish> collect3 = menu.stream()
                .filter(d -> d.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(collect3);
```

### 二、映射

**映射（map)**

```java
//映射：它会接受一个函数作为参数。这个函数会被应用到每个元素上，并将其映
        //射成一个新的元素（使用映射一词，是因为它和转换类似，但其中的细微差别在于它是“创建一
        //个新版本”而不是去“修改”）。
        List<String> collect4 = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(collect4);
        //理解map:map会把函数应用到每个元素上
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println(wordLengths);
```

**流的扁平化（flatMap）**

```java
 //流的扁平化flatMap：各个数组并不是分别映射成一个流，而是映射成流的内容。
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfwords = Arrays.stream(arrayOfWords);
        //使用map(Arrays::stream)时生成的是一个个的单个流
        List<String> wordList = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Stream<String>> collect5 = wordList.stream()
                .map(s -> s.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect5);
        //使用flatMap(Arrays::stream)时是把map(Arrays::stream)时生成的单个流都被合并起来，
	    //即扁平化为一个流
        List<String> collect6 = wordList.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(collect6);
```

![image](https://gitee.com/best-bo-cai/picture/raw/master/img/image-20211201161641507.png)

```java
 /**
         * 流的扁平化flatMap练习题：
         *  1：给定两个数字列表，如何返回所有的数对呢？例如，给定列表[1, 2, 3]和列表[3, 4]，应
         * 该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]。为简单起见，你可以用有两个元素的数组来代
         * 表数对。
         *  2：如何扩展前一个例子，只返回总和能被3整除的数对呢？例如(2, 4)和(3, 3)是可以的。
         */
        //1:答案
        List<Integer> num1 = Arrays.asList(1, 2, 3);
        List<Integer> num2 = Arrays.asList(3, 4);
        List<int[]> collect7 = num1.stream()
                .flatMap(i -> num2.stream()
                         .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        //2:答案
        List<int[]> collect8 = num1.stream()
                .flatMap(i -> num2.stream()
                         .filter(j -> (i + j) % 3 == 0)
                         .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
```

### 三、查找和匹配

查看数据集中的某些元素是否匹配一个给定的属性：`Stream API提供了allMatch、anyMatch、noneMatch、findFirst和findAny方法`

```java
//anyMatch：流中是否有一个元素能匹配给定的谓词
        //菜单里面是否有素食可选择
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("菜单含有素食");
        }
        //allMatch:流中的元素是否都能匹配给定的谓词
        if (menu.stream().allMatch(d -> d.getCalories() < 1000)) {
            System.out.println("食物热量都小于1000");
        }
        //noneMatch:确保流中没有任何元素与给定的谓词匹配,与allMatch相反
        if (menu.stream().noneMatch(d -> d.getCalories() >= 1000)) {
            System.out.println("食物热量没有一个大于等于1000的");
        }

//findAny:返回当前流中的任意元素,找到一个结果立即结束
        Optional<Dish> any = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        System.out.println(any);
        //findFirst:查找符合条件的第一个元素
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        //查找第一个平方数能被三整除的
        Optional<Integer> first = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst();
        System.out.println(first);
```

### 四、归约

`reduce`：把流中的元素组合起来。reduce意思为减少、缩小之意。

> 归约操作：此类查询需要将中所有元素反复结合起来，得到一个值，比如一个Integer。这样的查询可以被归类为归约操作
>
> （将流归约成一个值）。用函数式编程语言的术语来说，这称为折叠（fold），因为你可以将这个操
>
> 作看成把一张长长的纸（你的流）反复折叠成一个小方块，而这就是折叠操作的结果。

```java
//元素求和
//在原来求和是通过for-each循环来操作的
int sum = 0;
for (int x : numbers) {
    sum += x;
}
//用reduce这样写
Integer reduce = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(reduce);

//对比以上，这里reduce接受两个参数：
//1.第一个参数是：一个初始值0，对应于for-each循环中的sum的值。
//2.第二个参数是：一个BinaryOperator<T>来将两个元素结合起来产生一个新值，
//这里对应于for-each循环内部的sum+x;

//相比较下，我们写成乘法是这样的
Integer reduce2 = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println(reduce2);
```

> reduce操作过程：以加法为例，首先把0给a,然后从流中获取一个值给b，再与a相加，然后把这个新值与流中的下一个值相加，以此类推，得到最终的结果。![image-20211203094520958](https://gitee.com/best-bo-cai/picture/raw/master/img/image-20211203094520958.png)

``` java 
//最大值和最小值
//reduce还有一种变种写法，如求和的另一种写法
 Optional<Integer> reduce1 = numbers.stream().reduce(Integer::sum);
//对比以上例子，通过reduce还可以计算最大值最小值
Optional<Integer> reduce3 = numbers.stream().reduce((x, y) -> x > y ? x : y);
Optional<Integer> reduce3 = numbers.stream().reduce((x, y) -> x < y ? x : y);
```

![image-20211203095650751](https://gitee.com/best-bo-cai/picture/raw/master/img/image-20211203095650751.png)

> 一个map,reduce的例子：数一数流中有多少元素？
>
> 先把元素都映射为1，然后想加，如下：
>
> int count = numbers.stream()
>
>  		.map(d -> 1) 
>
> ​		 .reduce(0, (a, b) -> a + b);
>
> ​	map和reduce的连接通常称为map-reduce模式，因Google用它来进行网络搜索而出名，
>
> 因为它很容易并行化。

### 五、数值流

```java
//求和，与reduce不同的是没有装箱的过程。
        int sum1 = menu.stream().mapToInt(Dish::getCalories).sum();
        System.out.println(sum1);
        //最大值，可以用orElse指定一个默认值，如果没有最大值那最大值默认是这个值
        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        int i = max.orElse(1);
        System.out.println(i);
        //数值范围，生成1-100的数，range不包含结尾的值，rangeClosed包含
        IntStream range = IntStream.range(1, 100);
        long count = range.count();
        System.out.println(count);
        IntStream intStream = IntStream.rangeClosed(1, 100);
        System.out.println(intStream.count());
        //将数值流转换为Stream
//        Stream<Integer> stream = intStream.boxed();
        //例子：勾股数
        Stream<int[]> stream1 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100)
                                .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        stream1.limit(5).forEach(t -> System.out.println(t[0] + "," + t[1] + "," + t[2]));

        Stream.iterate(2, n -> n + n)
                .limit(25)
                .forEach(j -> System.out.println(j));

        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] +","+ t[1] + ")"));
```

