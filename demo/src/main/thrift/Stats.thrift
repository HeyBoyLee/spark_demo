namespace java com.xiaomi.data

struct student{
    1:required string studentName, #学生姓名
    2:required string sex,         #性别
    3:required i64 age,            #学生年龄
}
struct banji{
    1:required string banjiName, #班级名称
    2:required list<student> allStudents, #所有学生
}
struct school {
    1:required string schoolName,
    2:required i64    age,
    3:required list<string> zhuanye, #所有专业
    4:required list<banji> allBanji, #所有班级
}