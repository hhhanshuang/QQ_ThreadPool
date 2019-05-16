package hh;

public class Protocol {

    //游戏包含敲门人和门内人两个角色。门内人的问话和回答有固定格式，敲门人的回答是变化的，所以用以下常量表示敲门人会话进程和状态

    private static final int WAITING=0; //等待
    private static final int SENTTALK=1; //发消息完成
    

    private int state=WAITING; //会话状态
    private int currentTalk=0; //计数
    private int flag=0;
    //以下两个数组分别存储敲门人的两次回答

    private String[] clues={"未来规划","夸夸我","祝福"};

    private String[] answers1={"凡事都有可能，永远别说永远。","我也不能替你规划，慢慢想。","无论走到哪里，都应该记住，过去都是假的，回忆是一条没有尽头的路，一切以往的春天都不复存在"};
    private String[] answers2={"最近你又瘦啦！","见到你很开心。","你笑起来真好看，真的。"};
    private String[] answers3={"健康快乐的冲浪女孩","但愿世间人无病，何妨架上药生尘。","如果再也不能见到你，祝你早安，午安，晚安。"};
    
    public String protocolWorking(String question) { //question门内人的问话
        String answer=null; //敲门人的回答
        switch (state) {
            case WAITING: //开始敲门
                answer="请选择三种话题：未来规划/祝福/夸夸我（退出请按q）";
                state=SENTTALK;
                break;
            case SENTTALK: //是否是那三种问题
                for(int i=0;i<3;i++){
                    if(question.equalsIgnoreCase(clues[i])){
                        if(i==0)
                            answer=answers1[currentTalk%3];
                        else if(i==1)
                            answer=answers2[currentTalk%3];
                        else
                            answer=answers3[currentTalk%3];
                        flag=1;
                        state=SENTTALK;
                        break;
                    }
                }
                if(flag==0&&question.equalsIgnoreCase("q")){
                    state=WAITING;
                    answer="Game Over! Goodbye!";
                    
                }
                else if (flag==0) {
                    answer="对不起，我帮不了你，你应该问：未来规划/祝福/夸夸我。那我们重新开始吧！";
                }
                else{
                    currentTalk++;
                }
                break;                           
        }
        return answer;   
    }//end protocolWorking
}

