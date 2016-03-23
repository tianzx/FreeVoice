package net.tianzx.freevoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import net.tianzx.freevoice.message.MsgBuilder;

public class FreeVoiceActivityAction {
	private FreeVoiceActivity activity = null;
	public FreeVoiceActivityAction(FreeVoiceActivity activity){
		this.activity = activity;
	}
	
	private UserModel selfUserModel = null;
	private String selfSimNum = "";
	private List<UserModel> onlineUserList = new ArrayList<UserModel>();
	private Map<String,UserModel> userMsg = new HashMap<String,UserModel>();
	
	
	public UserModel getUserModelBySimNum(String simNum){
		for(UserModel um:this.onlineUserList){
			if(um.getSimNum().equals(simNum)){
				return um;
			}
		}
		if(this.selfSimNum.equals(simNum)){
			return this.selfUserModel;
		}
		return null;
	}
	public UserModel getUserMsgBySimNum(String simNum){
		return this.userMsg.get(simNum);
	}
	public void saveUserMsg(String simNum,UserModel um){
		this.userMsg.put(simNum,um);
	}
	
	public List<UserModel> getOnlineUserList(){
		return this.onlineUserList;
	}
	
	public void changeUserOnline(UserModel um){
		if(!this.userListContainUser(um.getSimNum())){
			this.changeUserList(um, true);
		}else if(!this.onlineUserList.contains(um)){
			this.changeUserList(um,false);
		}
	}
	public void changeUserList(UserModel um,boolean createOrUpdate){
		if(createOrUpdate){
			this.onlineUserList.add(um);
		}else{
			int index = -1;
			for(int i=0;i<this.onlineUserList.size();i++){
				if(this.onlineUserList.get(i).getSimNum().equals(um.getSimNum())){
					index = i;
					break;
				}
			}
			if(index>=0){
				this.onlineUserList.remove(index);
				this.onlineUserList.add(um);
			}
		}
	}
	private boolean userListContainUser(String simNum){
		for(UserModel um : this.onlineUserList){
			if(um.getSimNum().equals(simNum)){
				return true;
			}
		}
		return false;
	}
	
	
	public void sendLoginMsg(){
		String msg = new MsgBuilder(MsgBuilder.ONLINE_REQUEST)
			.setIp(CommonData.Instance.getIP(activity))
			.setSimNum(getPhoneSimNum())
//			.setName(this.getSelfUserModel().getName())
			.setShowImg(""+this.getSelfUserModel().getShowImg())
			.buildMsg();
		System.out.println("now sendLoginMsg="+msg);
		sendMsg("",msg);
	}
	public void sendMsg(String toIp,String msg){
		new UdpSend(toIp, msg).start();
	}
	public UserModel getSelfUserModel(){
		if(selfUserModel==null){
			selfUserModel = UserBusiness.getBySimNum(activity, this.getPhoneSimNum());

			if(selfUserModel==null || selfUserModel.getSimNum()==null || selfUserModel.getSimNum().trim().length()==0){
				selfUserModel = new UserModel();
				int showImg = MyHelper.getHeadImgRandomNum();

				selfUserModel.setSimNum(this.getPhoneSimNum());
				selfUserModel.setName(Build.MODEL);
				selfUserModel.setShowImg(showImg);

				UserBusiness.createUser(activity, selfUserModel);
			}
		}
		
		return selfUserModel;
	}
	public String getPhoneSimNum(){
		if(this.selfSimNum==null || this.selfSimNum.trim().length()==0){
			TelephonyManager tm = (TelephonyManager)activity.getSystemService(Context.TELEPHONY_SERVICE);
			this.selfSimNum = tm.getSimSerialNumber();
		}
		return selfSimNum;
	}
	
}
