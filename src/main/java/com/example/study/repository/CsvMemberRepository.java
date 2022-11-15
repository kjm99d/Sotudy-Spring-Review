package com.example.study.repository;

import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class CsvMemberRepository implements MemberRepository {

    private Long seq = 0L;

    final String filePath = "C:\\users.csv";

    @Override
    public void save(Member member) {
        String strAbsPath = CreateFile(filePath);

        seq++;
        member.setId(seq);

        // 파일에 데이터를 쓰자
        try {
            FileWriter writer = new FileWriter(strAbsPath, true);
            writer.append(Long.toString(member.getId()) + ",");
            writer.append(member.getStrUserName() + ",");
            writer.append(member.getStrUserPassword() + "\n");

            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private String CreateFile(String strPath) {
        File f = new File(strPath);

        // 파일이 없다면, 새로운 파일 만들어라
        if (f.exists() == false)
        {
            try
            {
                f.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        String strAbsPath = f.getAbsolutePath();

        f = null;
        return strAbsPath;
    }

    @Override
    public String findById(Long id) {
        final String strAbsPath = CreateFile(filePath);
        try {
            FileReader reader = new FileReader(strAbsPath);
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line = br.readLine()) != null)
            {
                String[] block = line.split(",");

                if (block.length == 3 && Long.parseLong(block[0]) == id)
                {
                    return block[1];
                }

            }
        } catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public void clear() {
        File file = new File(filePath);
        file.delete();
        file = null;
    }

    @Override
    public int GetMemberCount()  {
        String strAbsPath = CreateFile(filePath);
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(strAbsPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        int nMember = 0;
        try {
            while (bufferedReader.readLine() != null) {
                nMember++;
            }
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return nMember;
    }
}
