for (int i = 0; i < fileList.size(); i++) {
   String srcName = ((File)fileList.get(i)).getPath();
   String desName =srcName.substring(src.length(), srcName.length());
   desName = des + desName;
   File dir=newFile(desName).getParentFile();
   mkdir(dir);
  
   if(!copyFile(srcName, dir.getPath())){
    return false;
   }
  }
  return true;
}
public static void main(String[] args) {

  FileCopy t = new FileCopy();
 System.out.println(t.copyFile("D:/aaa.txt","E:"));
  String src="D:/asdf";
  String des="E:/adf";
  System.out.println(t.copyDir(src,des));
  System.out.println(t.getMessage());

  111
  222222
  3333333
  44