package phonebook;

import java.io.*;
class TextDataBase extends DAO
{
    private String fileName;
    public TextDataBase(String name)
    {
        fileName=name;
        File file=new File(fileName);
        if(!file.exists())
        {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    @Override
    public void addAnEntry(String name, String number) {
        String data = String.format("%s %s\n", name, number);
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            writer.append(data);
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String searchPhoneNumber(String name) {
        BufferedReader br=null;
        try {
            br=new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName),"utf-8"));
            while(br.ready())
            {
                String[] row=br.readLine().split(" ");
                if(row[0].equals(name))
                    return row[1];
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public String editData(String name, String number) {
        String result=null;
        //Read
        String strRow="";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(
                        new FileInputStream(fileName), "utf-8"));
            String line;
            while((line=br.readLine())!=null)
            {
                String[] row=line.split(" ");
                if(row[0].equals(name))
                    row[1]=number;
                strRow+=String.format("%s %s\r\n", row[0], row[1]);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            result="";
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                result="";
                e.printStackTrace();
            }
        }
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            writer.write(strRow);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            result="";
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                result="";
                e.printStackTrace();
            }
        }
        if(result==null)
            result=EDIT_RESULT;
        return result;
    }

    @Override
    public String deleteData(String name) {
        String result=null;
        //Read
        BufferedReader br=null;
        String allData="";
        try {
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(fileName), "utf-8"));
            String line;
            while((line=br.readLine())!=null)
            {
                String[] row=line.split(" ");
                if(!row[0].equals(name))
                    allData+=String.format("%s %s\r\n", row[0], row[1]);
            }
            br.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            result="";
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                result="";
                e.printStackTrace();
            }
        }
        //Write
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"));
            writer.write(allData);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                result="";
                e.printStackTrace();
            }
        }
        if(result==null)
            result = DELETE_RESULT;
        return result;
    }

}