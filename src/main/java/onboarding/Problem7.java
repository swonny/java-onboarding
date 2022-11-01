package onboarding;

import java.util.*;

public class Problem7 {
    public static ArrayList<String> sort(HashMap<String, Integer> sharedFriends) {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(sharedFriends.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() > o2.getValue()) {
                    return -1;
                }
                else if (o1.getValue() < o2.getValue()) {
                    return 1;
                }

                return o1.getKey().compareTo(o2.getKey());
            }
        });

        ArrayList<String> sortedMap = new ArrayList<>();
        for(Iterator<Map.Entry<String, Integer>> iter = list.iterator(); iter.hasNext();){
            Map.Entry<String, Integer> entry = iter.next();
            sortedMap.add(entry.getKey());
        }
        return sortedMap;
    }

    public static HashMap<String, Integer> checkVisitors(List<String> visitors, HashMap<String, Integer> sharedFrineds, HashSet<String> userFriends) {
        for(String visitor : visitors) {
            if(userFriends.contains(visitor)) {
                continue;
            }
            if(sharedFrineds.containsKey(visitor)) {
                sharedFrineds.replace(visitor, sharedFrineds.get(visitor), sharedFrineds.get(visitor) + 1);
            } else {
                sharedFrineds.put(visitor, 1);
            }
        }
        return sharedFrineds;
    }

    public static HashMap<String, Integer> createSharedFriendsHashmap(List<List<String>> friends, HashSet<String> userFriends, String user) {
        HashMap<String, Integer> sharedFriends = new HashMap<String, Integer>();
        for(String userFriend : userFriends) {
            for(List<String> friend : friends) {
                String firstName = friend.get(0);
                String secondName = friend.get(1);
                if(firstName == user || secondName == user) {
                    continue;
                } else if(firstName == userFriend || secondName == userFriend) {
                    String key = (firstName == userFriend) ? secondName : firstName;
                    if(sharedFriends.containsKey(key)) {
                        sharedFriends.replace(key, sharedFriends.get(key), sharedFriends.get(key) + 10);
                    } else {
                        sharedFriends.put(key, 10);
                    }
                }
            }
        }
        return sharedFriends;
    }

    public static HashSet<String> findUserFriends(String user, List<List<String>> friends) {
        HashSet<String> userFriend = new HashSet<String>();
        for(List<String> friend :  friends) {
            if(friend.get(0) == user) {
                userFriend.add(friend.get(1));
            } else if(friend.get(1) == user) {
                userFriend.add(friend.get(0));
            }
        }
        return userFriend;
    }

    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        HashSet<String> userFriends = findUserFriends(user, friends);
        HashMap<String, Integer> sharedFriendsMap = createSharedFriendsHashmap(friends, userFriends, user);
        checkVisitors(visitors, sharedFriendsMap, userFriends);
        answer = sort(sharedFriendsMap);
        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        String user = "mrko";
        List<List<String>> friends = List.of(
                List.of("donut", "jun"),
                List.of("donut", "mrko"),
                List.of("donut", "andole"),
                List.of("shakevan", "andole"),
                List.of("shakevan", "jun"),
                List.of("shakevan", "mrko")
        );
        List<String> visitors = List.of("bedi", "bedi", "donut", "bedi", "shakevan");
        solution(user, friends, visitors);
    }
}
