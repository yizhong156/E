import java.io.*;
importjava.util.ArrayList;
importjava.util.List;
public class FileCopy {
private Stringmessage = "";
public StringgetMessage() {
  return message;
}
public void setMessage(Stringmessage) {
  this.message = message;
}
/**
  * 将源文件拷贝到目标文件
  *
  * @param src
  *            写源文件地址，需文件名
  * @param des
  *            写目标文件地址，无需文件名
  */
public boolean copyFile(Stringsrc, String des) {
  File srcFile = new File(src);
  File desDir = new File(des);
  File desFile = new File(des +"/" + srcFile.getName());
  //判断源文件是否存在
  if (!srcFile.exists()) {
   this.setMessage("源文件不存在！");
   return false;
  } else if (!srcFile.isFile()) {
   this.setMessage("源文件格式错！");
   return false;
  }
  //判断源文件是否存在
  if (!desDir.exists()) {
   this.setMessage("目标目录不存在！");
   return false;
  } else if (!desDir.isDirectory()) {
   this.setMessage("不是有效的目录！");
   return false;
  }
  BufferedReader reader = null;
  BufferedWriter writer = null;
  String str;
  try {
   reader = new BufferedReader(new FileReader(srcFile));
   writer = new BufferedWriter(new FileWriter(desFile));
   //判断目标文件是否存在及其格式，不存在就创建，格式不对先删除，存在就替代
   if (!desFile.exists() ||!desFile.isFile()) {
    if (desFile.exists()) {
     desFile.delete();
    }
    desFile.createNewFile();
   }
   //从源文件读取数据，并写入目标文件
   str =reader.readLine();
   while (str != null) {
    writer.write(str);
    writer.newLine();
    str = reader.readLine();
   }
  } catch (IOException e) {
   this.setMessage(e.getMessage());
   return false;
  } finally {
   if (reader != null) {
    try {
     reader.close();
    } catch (IOException e) {
     this.setMessage(e.getMessage());
    }
   }
   if (writer != null) {
    try {
     writer.close();
    } catch (IOException e) {
     this.setMessage(e.getMessage());
    }
   }
  }
  return true;
}
private ListfileList = newArrayList();

/**
  * 列出所有文件
  * @param srcFile
  */
private void file(File srcFile) {
  if (srcFile.isDirectory()) {
   String[] files = srcFile.list();
  
   for (int i = 0; i < files.length; i++) {
    File f = new File(srcFile +"/" + files[i]);
    //如果是文件加入列表，否则递归列出
    if (f.isFile()) {
     fileList.add(f);
    } else
     file(f);
   }
  }else this.setMessage(srcFile.getAbsolutePath()+"不是目录");
}
/**
  * 建立目录
  * @param des
  * @throws IOException
  */private void mkdir(File des) {
  if (!des.exists() || !des.isDirectory()) {
   mkdir(des.getParentFile());
   if (des.exists()) {
    des.delete();
   }
   des.mkdir();
  }
}
/**
  * 复制目录 将源目录下所有文件拷贝到目标目录下
  * @param src  源目录
   * @param des  目标目录
  */
public boolean copyDir(String src,String des) {
  File srcFile = new File(src);
  if (!srcFile.exists()) {
   this.setMessage("源目录不存在！");
   return false;
  } else if (!srcFile.isDirectory()) {
   this.setMessage(src+"不是有效的目录！");
   return false;
  }
  file(srcFile);
  
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
}

}

 

/**
  * 写入日志
  * filePath 日志文件的路径
  * code 要写入日志文件的内容
  */
 public static boolean print(String filePath,String code) {
  try {
   File tofile=newFile(filePath);
   FileWriter fw=new FileWriter(tofile,true);
   BufferedWriter bw=newBufferedWriter(fw);
   PrintWriter pw=newPrintWriter(bw);
  
  System.out.println(getDate()+":"+code);
  pw.println(getDate()+":"+code);
   pw.close();
   bw.close();
   fw.close();
   return true;
  } catch (IOException e) {
   return false;
  }
 }

 

 /**
     * 拷贝一个目录或者文件到指定路径下
     *
     * @param source
     * @param target
     */
    public static void copy(File source, File target)
    {
        File tarpath = new File(target,source.getName());
        if (source.isDirectory())
        {
            tarpath.mkdir();
            File[] dir =source.listFiles();
            for (int i = 0; i <dir.length; i++)
            {
                copy(dir[i],tarpath);
            }
        }
        else
        {
            try
            {
                InputStream is = newFileInputStream(source);
                OutputStream os = newFileOutputStream(tarpath);
                byte[] buf = new byte[1024];
                int len = 0;
                while ((len = is.read(buf))!= -1)
                {
                    os.write(buf, 0,len);
                }
                is.close();
                os.close();
            }
            catch(FileNotFoundException e)
            {
               e.printStackTrace();
            }
            catch (IOExceptione)
            {
               e.printStackTrace();
            }
        }
    }

 

// 判断一个文件是否为二进制文件

public static booleanisBinary(File file) {

boolean isBinary =false;

try {

FileInputStream fin =new FileInputStream(file);

long len =file.length();

                      for (int j = 0; j < (int) len; j++) {

int t = fin.read();

if (t < 32&& t != 9 && t != 10 && t != 13) {

isBinary = true;

break;

}

}

} catch (Exception e){

e.printStackTrace();

}

return isBinary;

}

 

 