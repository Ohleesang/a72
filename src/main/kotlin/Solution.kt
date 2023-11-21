/**5~50000 player : 1등부터 현재 등수 순서대로 담은 문자열 배열
 * 2~1000000 calings : 해설진이 부른 이름을 담은 문자열배열
 *
 * 해설진들은 선수들이 자기 바로 앞의 선수를 추월할 때 추월한 선수 이름을 부른다.
 */

class Solution {
    fun solution(players: Array<String>, callings: Array<String>): Array<String> {
        var answer: Array<String> = arrayOf<String>()

        var maps = players.associateBy({it},{players.indexOf(it)+1}).toMutableMap()
        //자료구조 맵 으로 저장 맵(이름,순위)
        var num = 0
        for(i in callings.indices){//해설진이 부른 선수들 위치를 변환
            num = maps.get(callings[i])!!//부른 선수의 순위를 구함
            if(num==1) continue //1등이면 굳이 순위를 바꿀 필요가 없으니까
            maps.replace(maps.entries.find{it.value == num-1}?.key.toString(),num) // 앞에 있는 선수
            maps.replace(callings[i],num-1)//부른 선수 순위

        }
        maps = maps.toSortedMap(compareBy({maps[it]}))// 맵의 값들을 비교하여 정렬
        answer = maps.keys.toTypedArray()//key값(이름)을 따로 저장
        return answer
    }
}

fun main(){
    var a = Solution()
    a.solution(arrayOf("mumu", "soe", "poe", "kai", "mine"),arrayOf("kai", "kai", "mine", "mine"))
    //"mumu", "kai", "mine", "soe", "poe"
}