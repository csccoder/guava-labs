# utilites
- Joiner
  - 字符串拼接
- Splitter
  - 根据指定的分隔符拆分字符串，拆分结果可以转换为Iterable,List,Stream,Map等
- Preconditions
  - checkNotNull 如果为空，抛出NullPointException
  - checkArguments 如果expression为false,抛出IllegalArgumentException
  - checkState 如果expression为false,抛出IllegalStateException
  - checkElementIndex 如果数组/list下标越界，IndexOutOfBoundsException