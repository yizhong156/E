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
  * ��Դ�ļ�������Ŀ���ļ�
  *
  * @param src
  *            дԴ�ļ���ַ�����ļ���
  * @param des
  *            дĿ���ļ���ַ�������ļ���
  */
public boolean copyFile(Stringsrc, String des) {
  File srcFile = new File(src);
  File desDir = new File(des);
  File desFile = new File(des +"/" + srcFile.getName());
  //�ж�Դ�ļ��Ƿ����
  if (!srcFile.exists()) {
   this.setMessage("Դ�ļ������ڣ�");
   return false;
  } else if (!srcFile.isFile()) {
   this.setMessage("Դ�ļ���ʽ��");
   return false;
  }
  //�ж�Դ�ļ��Ƿ����
  if (!desDir.exists()) {
   this.setMessage("Ŀ��Ŀ¼�����ڣ�");
   return false;
  } else if (!desDir.isDirectory()) {
   this.setMessage("������Ч��Ŀ¼��");
   return false;
  }
  BufferedReader reader = null;
  BufferedWriter writer = null;
  String str;
  try {
   reader = new BufferedReader(new FileReader(srcFile));
   writer = new BufferedWriter(new FileWriter(desFile));
   //�ж�Ŀ���ļ��Ƿ���ڼ����ʽ�������ھʹ�������ʽ������ɾ�������ھ����
   if (!desFile.exists() ||!desFile.isFile()) {
    if (desFile.exists()) {
     desFile.delete();
    }
    desFile.createNewFile();
   }
   //��Դ�ļ���ȡ���ݣ���д��Ŀ���ļ�
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
  * �г������ļ�
  * @param srcFile
  */
private void file(File srcFile) {
  if (srcFile.isDirectory()) {
   String[] files = srcFile.list();
  
   for (int i = 0; i < files.length; i++) {
    File f = new File(srcFile +"/" + files[i]);
    //������ļ������б�����ݹ��г�
    if (f.isFile()) {
     fileList.add(f);
    } else
     file(f);
   }
  }else this.setMessage(srcFile.getAbsolutePath()+"����Ŀ¼");
}
/**
  * ����Ŀ¼
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
  * ����Ŀ¼ ��ԴĿ¼�������ļ�������Ŀ��Ŀ¼��
  * @param src  ԴĿ¼
   * @param des  Ŀ��Ŀ¼
  */
public boolean copyDir(String src,String des) {
  File srcFile = new File(src);
  if (!srcFile.exists()) {
   this.setMessage("ԴĿ¼�����ڣ�");
   return false;
  } else if (!srcFile.isDirectory()) {
   this.setMessage(src+"������Ч��Ŀ¼��");
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
  * д����־
  * filePath ��־�ļ���·��
  * code Ҫд����־�ļ�������
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
     * ����һ��Ŀ¼�����ļ���ָ��·����
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

 

// �ж�һ���ļ��Ƿ�Ϊ�������ļ�

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

 

 